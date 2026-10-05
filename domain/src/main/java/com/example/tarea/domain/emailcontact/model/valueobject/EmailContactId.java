package com.example.tarea.domain.emailcontact.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado EmailContact (value object).
 */
public record EmailContactId(UUID value) {

    public EmailContactId {
        if (value == null) {
            throw new DomainValidationException("EmailContactId value must not be null");
        }
    }

    public static EmailContactId generate() {
        return new EmailContactId(UUID.randomUUID());
    }

    public static EmailContactId of(UUID value) {
        return new EmailContactId(value);
    }
}
