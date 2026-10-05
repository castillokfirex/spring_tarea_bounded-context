package com.example.tarea.domain.chatairunerror.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatAiRunError (value object).
 */
public record ChatAiRunErrorId(UUID value) {

    public ChatAiRunErrorId {
        if (value == null) {
            throw new DomainValidationException("ChatAiRunErrorId value must not be null");
        }
    }

    public static ChatAiRunErrorId generate() {
        return new ChatAiRunErrorId(UUID.randomUUID());
    }

    public static ChatAiRunErrorId of(UUID value) {
        return new ChatAiRunErrorId(value);
    }
}
