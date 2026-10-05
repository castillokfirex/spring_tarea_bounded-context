package com.example.tarea.domain.phonecontact.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado PhoneContact (value object).
 */
public record PhoneContactId(UUID value) {

    public PhoneContactId {
        if (value == null) {
            throw new DomainValidationException("PhoneContactId value must not be null");
        }
    }

    public static PhoneContactId generate() {
        return new PhoneContactId(UUID.randomUUID());
    }

    public static PhoneContactId of(UUID value) {
        return new PhoneContactId(value);
    }
}
