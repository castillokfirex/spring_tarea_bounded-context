package com.example.tarea.application.common.exception;

/**
 * Se lanza cuando una operacion viola una regla de unicidad.
 */
public class ConflictApplicationException extends ApplicationException {

    public ConflictApplicationException(String message) {
        super(message);
    }
}
