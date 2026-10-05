package com.example.tarea.domain.medicationroute.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado MedicationRoute (value object).
 */
public record MedicationRouteId(UUID value) {

    public MedicationRouteId {
        if (value == null) {
            throw new DomainValidationException("MedicationRouteId value must not be null");
        }
    }

    public static MedicationRouteId generate() {
        return new MedicationRouteId(UUID.randomUUID());
    }

    public static MedicationRouteId of(UUID value) {
        return new MedicationRouteId(value);
    }
}
