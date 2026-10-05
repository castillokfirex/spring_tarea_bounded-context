package com.example.tarea.domain.chatparticipant.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatParticipant (value object).
 */
public record ChatParticipantId(UUID value) {

    public ChatParticipantId {
        if (value == null) {
            throw new DomainValidationException("ChatParticipantId value must not be null");
        }
    }

    public static ChatParticipantId generate() {
        return new ChatParticipantId(UUID.randomUUID());
    }

    public static ChatParticipantId of(UUID value) {
        return new ChatParticipantId(value);
    }
}
