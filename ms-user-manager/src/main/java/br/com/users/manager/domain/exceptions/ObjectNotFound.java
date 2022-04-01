package br.com.users.manager.domain.exceptions;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound() {
    }

    public ObjectNotFound(String message) {
        super(message);
    }
}
