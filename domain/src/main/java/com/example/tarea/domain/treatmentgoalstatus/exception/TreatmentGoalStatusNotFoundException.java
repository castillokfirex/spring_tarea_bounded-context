package com.example.tarea.domain.treatmentgoalstatus.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public class TreatmentGoalStatusNotFoundException extends ResourceNotFoundException {

    public TreatmentGoalStatusNotFoundException(TreatmentGoalStatusId id) {
        super("TreatmentGoalStatus with id '" + id.value() + "' was not found");
    }
}
