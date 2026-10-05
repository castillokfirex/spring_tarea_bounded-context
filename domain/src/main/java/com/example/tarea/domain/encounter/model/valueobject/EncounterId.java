package com.example.tarea.domain.encounter.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Encounter (value object).
 */
public record EncounterId(UUID value) {

    public EncounterId {
        if (value == null) {
            throw new DomainValidationException("EncounterId value must not be null");
        }
    }

    public static EncounterId generate() {
        return new EncounterId(UUID.randomUUID());
    }

    public static EncounterId of(UUID value) {
        return new EncounterId(value);
    }
}
