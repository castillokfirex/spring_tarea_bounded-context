package com.example.tarea.domain.assessmenttype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado AssessmentType (value object).
 */
public record AssessmentTypeId(UUID value) {

    public AssessmentTypeId {
        if (value == null) {
            throw new DomainValidationException("AssessmentTypeId value must not be null");
        }
    }

    public static AssessmentTypeId generate() {
        return new AssessmentTypeId(UUID.randomUUID());
    }

    public static AssessmentTypeId of(UUID value) {
        return new AssessmentTypeId(value);
    }
}
