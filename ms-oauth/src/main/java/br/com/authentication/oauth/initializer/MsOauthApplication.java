package br.com.authentication.oauth.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RefreshScope
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "br.com.authentication.oauth" , "br.com.authentication.*", "br.com.*"})
@EnableFeignClients(basePackages = "br.com.*")
public class MsOauthApplication implements CommandLineRunner {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MsOauthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

}
