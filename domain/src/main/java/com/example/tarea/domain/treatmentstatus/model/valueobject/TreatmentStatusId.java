package com.example.tarea.domain.treatmentstatus.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado TreatmentStatus (value object).
 */
public record TreatmentStatusId(UUID value) {

    public TreatmentStatusId {
        if (value == null) {
            throw new DomainValidationException("TreatmentStatusId value must not be null");
        }
    }

    public static TreatmentStatusId generate() {
        return new TreatmentStatusId(UUID.randomUUID());
    }

    public static TreatmentStatusId of(UUID value) {
        return new TreatmentStatusId(value);
    }
}
