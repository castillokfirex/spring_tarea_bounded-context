package com.example.tarea.domain.airunstatus.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado AiRunStatus (value object).
 */
public record AiRunStatusId(UUID value) {

    public AiRunStatusId {
        if (value == null) {
            throw new DomainValidationException("AiRunStatusId value must not be null");
        }
    }

    public static AiRunStatusId generate() {
        return new AiRunStatusId(UUID.randomUUID());
    }

    public static AiRunStatusId of(UUID value) {
        return new AiRunStatusId(value);
    }
}
