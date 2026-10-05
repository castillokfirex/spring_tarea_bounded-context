package com.example.tarea.domain.sendertype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado SenderType (value object).
 */
public record SenderTypeId(UUID value) {

    public SenderTypeId {
        if (value == null) {
            throw new DomainValidationException("SenderTypeId value must not be null");
        }
    }

    public static SenderTypeId generate() {
        return new SenderTypeId(UUID.randomUUID());
    }

    public static SenderTypeId of(UUID value) {
        return new SenderTypeId(value);
    }
}
