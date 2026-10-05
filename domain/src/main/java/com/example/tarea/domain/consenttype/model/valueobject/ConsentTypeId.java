package com.example.tarea.domain.consenttype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ConsentType (value object).
 */
public record ConsentTypeId(UUID value) {

    public ConsentTypeId {
        if (value == null) {
            throw new DomainValidationException("ConsentTypeId value must not be null");
        }
    }

    public static ConsentTypeId generate() {
        return new ConsentTypeId(UUID.randomUUID());
    }

    public static ConsentTypeId of(UUID value) {
        return new ConsentTypeId(value);
    }
}
