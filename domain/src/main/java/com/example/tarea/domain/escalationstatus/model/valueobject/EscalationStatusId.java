package com.example.tarea.domain.escalationstatus.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado EscalationStatus (value object).
 */
public record EscalationStatusId(UUID value) {

    public EscalationStatusId {
        if (value == null) {
            throw new DomainValidationException("EscalationStatusId value must not be null");
        }
    }

    public static EscalationStatusId generate() {
        return new EscalationStatusId(UUID.randomUUID());
    }

    public static EscalationStatusId of(UUID value) {
        return new EscalationStatusId(value);
    }
}
