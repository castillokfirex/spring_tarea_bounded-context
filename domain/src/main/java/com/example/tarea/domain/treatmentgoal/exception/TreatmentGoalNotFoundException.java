package com.example.tarea.domain.treatmentgoal.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public class TreatmentGoalNotFoundException extends ResourceNotFoundException {

    public TreatmentGoalNotFoundException(TreatmentGoalId id) {
        super("TreatmentGoal with id '" + id.value() + "' was not found");
    }
}
