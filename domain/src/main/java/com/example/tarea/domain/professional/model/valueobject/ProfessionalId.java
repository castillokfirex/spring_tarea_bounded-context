package com.example.tarea.domain.professional.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Professional (value object).
 */
public record ProfessionalId(UUID value) {

    public ProfessionalId {
        if (value == null) {
            throw new DomainValidationException("ProfessionalId value must not be null");
        }
    }

    public static ProfessionalId generate() {
        return new ProfessionalId(UUID.randomUUID());
    }

    public static ProfessionalId of(UUID value) {
        return new ProfessionalId(value);
    }
}
