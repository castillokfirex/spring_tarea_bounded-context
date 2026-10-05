package com.example.tarea.domain.clinicalnote.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ClinicalNote (value object).
 */
public record ClinicalNoteId(UUID value) {

    public ClinicalNoteId {
        if (value == null) {
            throw new DomainValidationException("ClinicalNoteId value must not be null");
        }
    }

    public static ClinicalNoteId generate() {
        return new ClinicalNoteId(UUID.randomUUID());
    }

    public static ClinicalNoteId of(UUID value) {
        return new ClinicalNoteId(value);
    }
}
