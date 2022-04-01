package br.com.users.manager.controller;

import br.com.users.manager.domain.dtos.ErrorDTO;
import br.com.users.manager.domain.exceptions.ObjectNotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ObjectNotFound.class})
    protected ResponseEntity<Object> handleObjectNotFound(
            RuntimeException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationException(
            RuntimeException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO(LocalDateTime.now(), "erro ao registrar recurso.", HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}