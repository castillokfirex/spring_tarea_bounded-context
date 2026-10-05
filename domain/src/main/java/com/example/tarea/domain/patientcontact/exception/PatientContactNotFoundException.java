package com.example.tarea.domain.patientcontact.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;

public class PatientContactNotFoundException extends ResourceNotFoundException {

    public PatientContactNotFoundException(PatientContactId id) {
        super("PatientContact with id '" + id.value() + "' was not found");
    }
}
