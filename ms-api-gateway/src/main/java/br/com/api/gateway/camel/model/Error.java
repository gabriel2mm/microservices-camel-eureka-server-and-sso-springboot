package br.com.api.gateway.camel.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Error {

    private int statusCode;
    private long timeStamp;
    private String message;
}
