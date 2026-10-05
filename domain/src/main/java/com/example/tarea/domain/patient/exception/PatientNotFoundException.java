package com.example.tarea.domain.patient.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.patient.model.valueobject.PatientId;

public class PatientNotFoundException extends ResourceNotFoundException {

    public PatientNotFoundException(PatientId id) {
        super("Patient with id '" + id.value() + "' was not found");
    }
}
