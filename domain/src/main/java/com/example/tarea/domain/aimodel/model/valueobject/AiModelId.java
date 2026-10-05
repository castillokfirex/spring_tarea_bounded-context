package com.example.tarea.domain.aimodel.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado AiModel (value object).
 */
public record AiModelId(UUID value) {

    public AiModelId {
        if (value == null) {
            throw new DomainValidationException("AiModelId value must not be null");
        }
    }

    public static AiModelId generate() {
        return new AiModelId(UUID.randomUUID());
    }

    public static AiModelId of(UUID value) {
        return new AiModelId(value);
    }
}
