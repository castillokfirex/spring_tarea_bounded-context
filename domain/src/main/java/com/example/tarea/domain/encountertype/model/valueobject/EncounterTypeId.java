package com.example.tarea.domain.encountertype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado EncounterType (value object).
 */
public record EncounterTypeId(UUID value) {

    public EncounterTypeId {
        if (value == null) {
            throw new DomainValidationException("EncounterTypeId value must not be null");
        }
    }

    public static EncounterTypeId generate() {
        return new EncounterTypeId(UUID.randomUUID());
    }

    public static EncounterTypeId of(UUID value) {
        return new EncounterTypeId(value);
    }
}
