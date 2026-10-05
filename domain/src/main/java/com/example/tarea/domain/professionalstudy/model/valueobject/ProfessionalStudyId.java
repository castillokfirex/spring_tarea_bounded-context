package com.example.tarea.domain.professionalstudy.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ProfessionalStudy (value object).
 */
public record ProfessionalStudyId(UUID value) {

    public ProfessionalStudyId {
        if (value == null) {
            throw new DomainValidationException("ProfessionalStudyId value must not be null");
        }
    }

    public static ProfessionalStudyId generate() {
        return new ProfessionalStudyId(UUID.randomUUID());
    }

    public static ProfessionalStudyId of(UUID value) {
        return new ProfessionalStudyId(value);
    }
}
