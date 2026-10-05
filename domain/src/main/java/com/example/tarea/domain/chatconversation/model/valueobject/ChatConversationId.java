package com.example.tarea.domain.chatconversation.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatConversation (value object).
 */
public record ChatConversationId(UUID value) {

    public ChatConversationId {
        if (value == null) {
            throw new DomainValidationException("ChatConversationId value must not be null");
        }
    }

    public static ChatConversationId generate() {
        return new ChatConversationId(UUID.randomUUID());
    }

    public static ChatConversationId of(UUID value) {
        return new ChatConversationId(value);
    }
}
