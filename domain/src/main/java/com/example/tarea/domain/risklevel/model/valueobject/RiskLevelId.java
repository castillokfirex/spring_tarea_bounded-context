package com.example.tarea.domain.risklevel.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado RiskLevel (value object).
 */
public record RiskLevelId(UUID value) {

    public RiskLevelId {
        if (value == null) {
            throw new DomainValidationException("RiskLevelId value must not be null");
        }
    }

    public static RiskLevelId generate() {
        return new RiskLevelId(UUID.randomUUID());
    }

    public static RiskLevelId of(UUID value) {
        return new RiskLevelId(value);
    }
}
