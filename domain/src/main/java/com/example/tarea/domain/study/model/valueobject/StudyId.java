package com.example.tarea.domain.study.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Study (value object).
 */
public record StudyId(UUID value) {

    public StudyId {
        if (value == null) {
            throw new DomainValidationException("StudyId value must not be null");
        }
    }

    public static StudyId generate() {
        return new StudyId(UUID.randomUUID());
    }

    public static StudyId of(UUID value) {
        return new StudyId(value);
    }
}
