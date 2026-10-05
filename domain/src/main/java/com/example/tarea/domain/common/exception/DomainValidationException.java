package com.example.tarea.domain.common.exception;

/**
 * Se lanza cuando se viola una invariante del dominio.
 */
public class DomainValidationException extends DomainException {

    public DomainValidationException(String message) {
        super(message);
    }
}
