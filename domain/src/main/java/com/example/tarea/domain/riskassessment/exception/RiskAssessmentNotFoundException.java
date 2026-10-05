package com.example.tarea.domain.riskassessment.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;

public class RiskAssessmentNotFoundException extends ResourceNotFoundException {

    public RiskAssessmentNotFoundException(RiskAssessmentId id) {
        super("RiskAssessment with id '" + id.value() + "' was not found");
    }
}
