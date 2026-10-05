package com.example.tarea.domain.encountermodality.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado EncounterModality (value object).
 */
public record EncounterModalityId(UUID value) {

    public EncounterModalityId {
        if (value == null) {
            throw new DomainValidationException("EncounterModalityId value must not be null");
        }
    }

    public static EncounterModalityId generate() {
        return new EncounterModalityId(UUID.randomUUID());
    }

    public static EncounterModalityId of(UUID value) {
        return new EncounterModalityId(value);
    }
}
