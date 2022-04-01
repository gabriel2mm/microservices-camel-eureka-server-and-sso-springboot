package br.com.spring.batch.exemple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.*, br.com.spring.batch.exemple.*")
public class MsSpringBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsSpringBatchApplication.class, args);
    }
}
