package com.example.tarea.domain.mentalstatusexam.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado MentalStatusExam (value object).
 */
public record MentalStatusExamId(UUID value) {

    public MentalStatusExamId {
        if (value == null) {
            throw new DomainValidationException("MentalStatusExamId value must not be null");
        }
    }

    public static MentalStatusExamId generate() {
        return new MentalStatusExamId(UUID.randomUUID());
    }

    public static MentalStatusExamId of(UUID value) {
        return new MentalStatusExamId(value);
    }
}
