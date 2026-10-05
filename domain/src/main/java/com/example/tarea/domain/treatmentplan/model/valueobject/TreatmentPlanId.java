package com.example.tarea.domain.treatmentplan.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado TreatmentPlan (value object).
 */
public record TreatmentPlanId(UUID value) {

    public TreatmentPlanId {
        if (value == null) {
            throw new DomainValidationException("TreatmentPlanId value must not be null");
        }
    }

    public static TreatmentPlanId generate() {
        return new TreatmentPlanId(UUID.randomUUID());
    }

    public static TreatmentPlanId of(UUID value) {
        return new TreatmentPlanId(value);
    }
}
