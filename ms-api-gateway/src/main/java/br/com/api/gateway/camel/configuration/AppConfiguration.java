package br.com.api.gateway.camel.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfiguration {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Bean
    @Primary
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(JWT_SECRET);

        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore (){
        return new JwtTokenStore(accessTokenConverter());
    }
}
