package com.example.tarea.domain.treatmentstatus.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentStatusNotFoundException extends ResourceNotFoundException {

    public TreatmentStatusNotFoundException(TreatmentStatusId id) {
        super("TreatmentStatus with id '" + id.value() + "' was not found");
    }
}
