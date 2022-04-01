package br.com.microservices.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.text.MessageFormat;


@EnableConfigServer
@SpringBootApplication
public class MsConfigurationsApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(MsConfigurationsApplication.class);

    @Value("${spring.cloud.config.server.git.username}")
    private String username;
    @Value("${spring.cloud.config.server.git.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(MsConfigurationsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(MessageFormat.format("Variáveis de ambientes iniciadas com sucesso! {0}, {1} ", username, password));
    }
}
