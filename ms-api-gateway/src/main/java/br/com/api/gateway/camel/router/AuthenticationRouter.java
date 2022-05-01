package br.com.api.gateway.camel.router;

import br.com.api.gateway.camel.model.AccessToken;
import br.com.api.gateway.camel.model.Login;
import br.com.api.gateway.camel.process.AuthenticationRequestProcessor;
import br.com.api.gateway.camel.process.AuthenticationResponseProcessor;
import br.com.api.gateway.camel.process.HttpErrorHandler;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class AuthenticationRouter extends RouteBuilder {

    private final DiscoveryClient discoveryClient;

    public AuthenticationRouter(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        rest("auth")
                .id("auth-post")
                .post()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .route()
                .streamCaching()
                .messageHistory()
                .marshal(new JacksonDataFormat(Login.class))
                .process(new AuthenticationRequestProcessor())
                .setHeader(HttpHeaders.CONTENT_TYPE, constant("application/x-www-form-urlencoded"))
                .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                .serviceCall("ms-oauth/oauth/token?bridgeEndpoint=true")
                .process(new AuthenticationResponseProcessor())
                .unmarshal().json(JsonLibrary.Jackson, AccessToken.class)
                .onException(HttpOperationFailedException.class)
                    .handled(true)
                    .process(new HttpErrorHandler())
                    .unmarshal().json()
                .endRest();

        rest("depricated").post().id("auth-post").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE).route().process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                if (discoveryClient == null || discoveryClient.getInstances("ms-oauth").size() <= 0) return;

                URL url = new URL(discoveryClient.getInstances("ms-oauth").get(0).getUri().toString() + "/oauth/token");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                Map<String, String> headers = new HashMap<>();
                if (exchange.getIn().getHeader("Authorization") != null && exchange.getIn().getHeader("Authorization").toString().trim().length() > 0) {
                    headers.put("Authorization", exchange.getIn().getHeader("Authorization").toString());
                }
                if (exchange.getIn().getHeader("username") != null && exchange.getIn().getHeader("username").toString().trim().length() > 0) {
                    headers.put("username", exchange.getIn().getHeader("username").toString());
                }
                if (exchange.getIn().getHeader("password") != null && exchange.getIn().getHeader("password").toString().trim().length() > 0) {
                    headers.put("password", exchange.getIn().getHeader("password").toString());
                }
                if (exchange.getIn().getHeader("grant_type") != null && exchange.getIn().getHeader("grant_type").toString().trim().length() > 0) {
                    headers.put("grant_type", exchange.getIn().getHeader("grant_type").toString());
                }
                if (exchange.getIn().getHeader("cookie") != null && exchange.getIn().getHeader("cookie").toString().trim().length() > 0) {
                    headers.put("cookie", exchange.getIn().getHeader("cookie").toString());
                    exchange.getOut().setHeader("cookie", headers.get("cookie"));
                }

                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }

                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                String postData = MessageFormat.format("username={0}&password={1}&grant_type={2}", headers.get("username") != null && !headers.get("username").isEmpty() ? headers.get("username") : "", headers.get("password") != null && !headers.get("password").isEmpty() ? headers.get("password") : "", headers.get("grant_type") != null && !headers.get("grant_type").isEmpty() ? headers.get("grant_type") : "");

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(postData);
                wr.flush();
                wr.close();

                int statusCode = httpURLConnection.getResponseCode();
                String response = "";
                if (statusCode == HttpStatus.OK.value()) {
                    response = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));

                } else {
                    response = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                }
                exchange.getOut().setHeader("Content-Type", "application/json");
                exchange.getOut().setBody(response);

            }
        }).log("${body}").convertBodyTo(String.class).unmarshal().json().endRest();

    }
}
