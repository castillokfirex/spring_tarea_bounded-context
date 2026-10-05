package com.example.tarea.domain.messagetype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado MessageType (value object).
 */
public record MessageTypeId(UUID value) {

    public MessageTypeId {
        if (value == null) {
            throw new DomainValidationException("MessageTypeId value must not be null");
        }
    }

    public static MessageTypeId generate() {
        return new MessageTypeId(UUID.randomUUID());
    }

    public static MessageTypeId of(UUID value) {
        return new MessageTypeId(value);
    }
}
