package com.example.tarea.domain.stateregion.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado StateRegion (value object).
 */
public record StateRegionId(UUID value) {

    public StateRegionId {
        if (value == null) {
            throw new DomainValidationException("StateRegionId value must not be null");
        }
    }

    public static StateRegionId generate() {
        return new StateRegionId(UUID.randomUUID());
    }

    public static StateRegionId of(UUID value) {
        return new StateRegionId(value);
    }
}
