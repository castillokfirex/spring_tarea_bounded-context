package com.example.tarea.domain.priority.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Priority (value object).
 */
public record PriorityId(UUID value) {

    public PriorityId {
        if (value == null) {
            throw new DomainValidationException("PriorityId value must not be null");
        }
    }

    public static PriorityId generate() {
        return new PriorityId(UUID.randomUUID());
    }

    public static PriorityId of(UUID value) {
        return new PriorityId(value);
    }
}
