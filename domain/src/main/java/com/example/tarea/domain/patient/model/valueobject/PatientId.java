package com.example.tarea.domain.patient.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Patient (value object).
 */
public record PatientId(UUID value) {

    public PatientId {
        if (value == null) {
            throw new DomainValidationException("PatientId value must not be null");
        }
    }

    public static PatientId generate() {
        return new PatientId(UUID.randomUUID());
    }

    public static PatientId of(UUID value) {
        return new PatientId(value);
    }
}
