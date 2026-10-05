package com.example.tarea.domain.clinicalrecord.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ClinicalRecord (value object).
 */
public record ClinicalRecordId(UUID value) {

    public ClinicalRecordId {
        if (value == null) {
            throw new DomainValidationException("ClinicalRecordId value must not be null");
        }
    }

    public static ClinicalRecordId generate() {
        return new ClinicalRecordId(UUID.randomUUID());
    }

    public static ClinicalRecordId of(UUID value) {
        return new ClinicalRecordId(value);
    }
}
