package com.project.sigpag.sigpagapi.domain.exception;

public class NegocioException extends RuntimeException{
    public NegocioException(String message) {
        super(message);
    }
}
