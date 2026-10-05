package com.example.tarea.domain.chatairun.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatAiRun (value object).
 */
public record ChatAiRunId(UUID value) {

    public ChatAiRunId {
        if (value == null) {
            throw new DomainValidationException("ChatAiRunId value must not be null");
        }
    }

    public static ChatAiRunId generate() {
        return new ChatAiRunId(UUID.randomUUID());
    }

    public static ChatAiRunId of(UUID value) {
        return new ChatAiRunId(value);
    }
}
