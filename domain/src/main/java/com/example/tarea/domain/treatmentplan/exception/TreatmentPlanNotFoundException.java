package com.example.tarea.domain.treatmentplan.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public class TreatmentPlanNotFoundException extends ResourceNotFoundException {

    public TreatmentPlanNotFoundException(TreatmentPlanId id) {
        super("TreatmentPlan with id '" + id.value() + "' was not found");
    }
}
