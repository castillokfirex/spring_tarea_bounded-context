package com.example.tarea.domain.professionaltype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ProfessionalType (value object).
 */
public record ProfessionalTypeId(UUID value) {

    public ProfessionalTypeId {
        if (value == null) {
            throw new DomainValidationException("ProfessionalTypeId value must not be null");
        }
    }

    public static ProfessionalTypeId generate() {
        return new ProfessionalTypeId(UUID.randomUUID());
    }

    public static ProfessionalTypeId of(UUID value) {
        return new ProfessionalTypeId(value);
    }
}
