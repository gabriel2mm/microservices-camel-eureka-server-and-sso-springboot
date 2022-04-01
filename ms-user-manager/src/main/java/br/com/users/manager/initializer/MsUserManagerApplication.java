package br.com.users.manager.initializer;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RefreshScope
@EnableEurekaClient
@EntityScan(basePackages = "br.com.users.*")
@EnableJpaRepositories(basePackages = "br.com.users.*")
@SpringBootApplication(scanBasePackages = "br.com.users.*")
public class MsUserManagerApplication{

    public static void main(String[] args) {
        SpringApplication.run(MsUserManagerApplication.class, args);
    }
}
