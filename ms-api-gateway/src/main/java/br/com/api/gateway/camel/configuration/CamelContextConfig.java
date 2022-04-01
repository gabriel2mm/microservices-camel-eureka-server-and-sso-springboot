package br.com.api.gateway.camel.configuration;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelContextConfig {

    private final ApplicationContext context;

    public CamelContextConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public CamelContext camelContext() {
        return new SpringCamelContext(context);
    }
}
