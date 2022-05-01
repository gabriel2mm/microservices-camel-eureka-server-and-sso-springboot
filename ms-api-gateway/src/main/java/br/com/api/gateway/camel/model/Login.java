package br.com.api.gateway.camel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Login {

    private String username;

    private String password;

    @JsonProperty("grant_type")
    private String grantType;
}
