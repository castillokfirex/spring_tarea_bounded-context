package com.example.tarea.domain.encounterstatus.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado EncounterStatus (value object).
 */
public record EncounterStatusId(UUID value) {

    public EncounterStatusId {
        if (value == null) {
            throw new DomainValidationException("EncounterStatusId value must not be null");
        }
    }

    public static EncounterStatusId generate() {
        return new EncounterStatusId(UUID.randomUUID());
    }

    public static EncounterStatusId of(UUID value) {
        return new EncounterStatusId(value);
    }
}
