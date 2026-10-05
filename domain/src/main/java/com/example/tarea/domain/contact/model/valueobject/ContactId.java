package com.example.tarea.domain.contact.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Contact (value object).
 */
public record ContactId(UUID value) {

    public ContactId {
        if (value == null) {
            throw new DomainValidationException("ContactId value must not be null");
        }
    }

    public static ContactId generate() {
        return new ContactId(UUID.randomUUID());
    }

    public static ContactId of(UUID value) {
        return new ContactId(value);
    }
}
