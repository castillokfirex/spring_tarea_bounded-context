package com.example.tarea.domain.chatescalation.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatEscalation (value object).
 */
public record ChatEscalationId(UUID value) {

    public ChatEscalationId {
        if (value == null) {
            throw new DomainValidationException("ChatEscalationId value must not be null");
        }
    }

    public static ChatEscalationId generate() {
        return new ChatEscalationId(UUID.randomUUID());
    }

    public static ChatEscalationId of(UUID value) {
        return new ChatEscalationId(value);
    }
}
