package com.example.tarea.domain.gender.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Gender (value object).
 */
public record GenderId(UUID value) {

    public GenderId {
        if (value == null) {
            throw new DomainValidationException("GenderId value must not be null");
        }
    }

    public static GenderId generate() {
        return new GenderId(UUID.randomUUID());
    }

    public static GenderId of(UUID value) {
        return new GenderId(value);
    }
}
