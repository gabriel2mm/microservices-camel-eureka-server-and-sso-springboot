package br.com.estudos.correios.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@RefreshScope
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "br.com.estudos.correios.*")
@EntityScan(basePackages = "br.com.estudos.correios.*")
@EnableJpaRepositories(basePackages = "br.com.estudos.correios.*")
public class CorreiosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorreiosApplication.class, args);
    }
}