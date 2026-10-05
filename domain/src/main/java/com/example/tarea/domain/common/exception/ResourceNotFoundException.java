package com.example.tarea.domain.common.exception;

/**
 * Clase base para las excepciones "no encontrado" de cada bounded context.
 */
public abstract class ResourceNotFoundException extends DomainException {

    protected ResourceNotFoundException(String message) {
        super(message);
    }
}
