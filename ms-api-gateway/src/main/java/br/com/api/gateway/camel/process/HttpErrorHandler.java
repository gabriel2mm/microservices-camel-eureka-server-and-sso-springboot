package br.com.api.gateway.camel.process;

import br.com.api.gateway.camel.model.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.HttpStatus;

import java.util.Date;

public class HttpErrorHandler implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Error error = new Error();
        error.setMessage("Unauthorized");
        error.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        error.setTimeStamp(new Date().getTime());
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] body = objectMapper.writeValueAsBytes(error);
        exchange.getMessage().setHeader(exchange.CONTENT_TYPE, "application/json");
        exchange.getMessage().setHeader(exchange.HTTP_RESPONSE_CODE, 401);
        exchange.getMessage().setBody(body);
    }
}
