package com.example.tarea.domain.treatmentgoalstatus.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado TreatmentGoalStatus (value object).
 */
public record TreatmentGoalStatusId(UUID value) {

    public TreatmentGoalStatusId {
        if (value == null) {
            throw new DomainValidationException("TreatmentGoalStatusId value must not be null");
        }
    }

    public static TreatmentGoalStatusId generate() {
        return new TreatmentGoalStatusId(UUID.randomUUID());
    }

    public static TreatmentGoalStatusId of(UUID value) {
        return new TreatmentGoalStatusId(value);
    }
}
