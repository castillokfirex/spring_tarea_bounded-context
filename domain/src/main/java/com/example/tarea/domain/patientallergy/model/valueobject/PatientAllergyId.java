package com.example.tarea.domain.patientallergy.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado PatientAllergy (value object).
 */
public record PatientAllergyId(UUID value) {

    public PatientAllergyId {
        if (value == null) {
            throw new DomainValidationException("PatientAllergyId value must not be null");
        }
    }

    public static PatientAllergyId generate() {
        return new PatientAllergyId(UUID.randomUUID());
    }

    public static PatientAllergyId of(UUID value) {
        return new PatientAllergyId(value);
    }
}
