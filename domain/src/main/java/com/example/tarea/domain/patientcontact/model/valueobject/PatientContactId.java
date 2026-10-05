package com.example.tarea.domain.patientcontact.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado PatientContact (value object).
 */
public record PatientContactId(UUID value) {

    public PatientContactId {
        if (value == null) {
            throw new DomainValidationException("PatientContactId value must not be null");
        }
    }

    public static PatientContactId generate() {
        return new PatientContactId(UUID.randomUUID());
    }

    public static PatientContactId of(UUID value) {
        return new PatientContactId(value);
    }
}
