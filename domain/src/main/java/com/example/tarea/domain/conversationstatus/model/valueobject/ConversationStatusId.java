package com.example.tarea.domain.conversationstatus.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ConversationStatus (value object).
 */
public record ConversationStatusId(UUID value) {

    public ConversationStatusId {
        if (value == null) {
            throw new DomainValidationException("ConversationStatusId value must not be null");
        }
    }

    public static ConversationStatusId generate() {
        return new ConversationStatusId(UUID.randomUUID());
    }

    public static ConversationStatusId of(UUID value) {
        return new ConversationStatusId(value);
    }
}
