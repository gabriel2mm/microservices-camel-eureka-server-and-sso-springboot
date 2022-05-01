package br.com.api.gateway.camel.process;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class AuthenticationResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("{}", exchange.getMessage().getBody());
    }
}
