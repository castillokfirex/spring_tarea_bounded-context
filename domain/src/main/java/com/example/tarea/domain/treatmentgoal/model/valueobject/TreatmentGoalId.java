package com.example.tarea.domain.treatmentgoal.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado TreatmentGoal (value object).
 */
public record TreatmentGoalId(UUID value) {

    public TreatmentGoalId {
        if (value == null) {
            throw new DomainValidationException("TreatmentGoalId value must not be null");
        }
    }

    public static TreatmentGoalId generate() {
        return new TreatmentGoalId(UUID.randomUUID());
    }

    public static TreatmentGoalId of(UUID value) {
        return new TreatmentGoalId(value);
    }
}
