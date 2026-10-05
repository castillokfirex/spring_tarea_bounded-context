package com.example.tarea.domain.chatmessage.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatMessage (value object).
 */
public record ChatMessageId(UUID value) {

    public ChatMessageId {
        if (value == null) {
            throw new DomainValidationException("ChatMessageId value must not be null");
        }
    }

    public static ChatMessageId generate() {
        return new ChatMessageId(UUID.randomUUID());
    }

    public static ChatMessageId of(UUID value) {
        return new ChatMessageId(value);
    }
}
