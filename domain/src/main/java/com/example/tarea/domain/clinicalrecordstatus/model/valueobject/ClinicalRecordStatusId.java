package com.example.tarea.domain.clinicalrecordstatus.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ClinicalRecordStatus (value object).
 */
public record ClinicalRecordStatusId(UUID value) {

    public ClinicalRecordStatusId {
        if (value == null) {
            throw new DomainValidationException("ClinicalRecordStatusId value must not be null");
        }
    }

    public static ClinicalRecordStatusId generate() {
        return new ClinicalRecordStatusId(UUID.randomUUID());
    }

    public static ClinicalRecordStatusId of(UUID value) {
        return new ClinicalRecordStatusId(value);
    }
}
