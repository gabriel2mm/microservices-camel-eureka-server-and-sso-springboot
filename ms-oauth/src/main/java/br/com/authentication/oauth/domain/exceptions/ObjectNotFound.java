package br.com.authentication.oauth.domain.exceptions;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound() {
    }

    public ObjectNotFound(String message) {
        super(message);
    }
}
