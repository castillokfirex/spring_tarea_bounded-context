package com.example.tarea.domain.chatescalationstatushistory.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatEscalationStatusHistory (value object).
 */
public record ChatEscalationStatusHistoryId(UUID value) {

    public ChatEscalationStatusHistoryId {
        if (value == null) {
            throw new DomainValidationException("ChatEscalationStatusHistoryId value must not be null");
        }
    }

    public static ChatEscalationStatusHistoryId generate() {
        return new ChatEscalationStatusHistoryId(UUID.randomUUID());
    }

    public static ChatEscalationStatusHistoryId of(UUID value) {
        return new ChatEscalationStatusHistoryId(value);
    }
}
