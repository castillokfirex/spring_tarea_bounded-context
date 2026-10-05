package com.example.tarea.domain.riskassessment.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado RiskAssessment (value object).
 */
public record RiskAssessmentId(UUID value) {

    public RiskAssessmentId {
        if (value == null) {
            throw new DomainValidationException("RiskAssessmentId value must not be null");
        }
    }

    public static RiskAssessmentId generate() {
        return new RiskAssessmentId(UUID.randomUUID());
    }

    public static RiskAssessmentId of(UUID value) {
        return new RiskAssessmentId(value);
    }
}
