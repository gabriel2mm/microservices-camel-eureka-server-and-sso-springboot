package br.com.api.gateway.camel.process;

import br.com.api.gateway.camel.model.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.MessageFormat;


@Slf4j
public class AuthenticationRequestProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String bodyStr = exchange.getIn().getBody(String.class);
        byte[] body = bodyStr.getBytes();
        ObjectMapper objectMapper = new ObjectMapper();
        Login login = objectMapper.readValue(body, Login.class);
        exchange.getMessage().setHeader("username", login.getUsername());
        exchange.getMessage().setHeader("password", login.getPassword());
        exchange.getMessage().setHeader("grant_type", login.getGrantType());
        exchange.getMessage().setHeader("Content-Type", "application/x-www-form-urlencoded");
        exchange.getMessage().setBody(MessageFormat.format("username={0}&password={1}&grant_type={2}", login.getUsername(),  login.getPassword(), login.getGrantType()));
    }
}
