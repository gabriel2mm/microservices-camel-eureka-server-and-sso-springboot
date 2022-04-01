package br.com.api.gateway.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CorreiosRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .host("localhost")
                .port("8081")
                .bindingMode(RestBindingMode.json);

        rest("zip-code" )
                .get()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .routeId("zipcode-get")
                .streamCaching()
                .convertBodyTo(String.class)
                .circuitBreaker().resilience4jConfiguration().timeoutEnabled(true).timeoutDuration(2000).end()
                .serviceCall("ms-search-zipcode/api/v1/zip-code?bridgeEndpoint=true")
                .unmarshal().json()
                .log("zipcode get: ${body}")
                .endRest();

        rest("zip-code" )
                .post()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .streamCaching()
                .marshal().json()
                .routeId("zipcode-post")
                .circuitBreaker().resilience4jConfiguration().timeoutEnabled(true).timeoutDuration(2000).end()
                .serviceCall("ms-search-zipcode/api/v1/zip-code?bridgeEndpoint=true")
                .unmarshal().json().log("${body}")
                .endRest();
    }
}
