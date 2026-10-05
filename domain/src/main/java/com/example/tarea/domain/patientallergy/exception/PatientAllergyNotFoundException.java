package com.example.tarea.domain.patientallergy.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;

public class PatientAllergyNotFoundException extends ResourceNotFoundException {

    public PatientAllergyNotFoundException(PatientAllergyId id) {
        super("PatientAllergy with id '" + id.value() + "' was not found");
    }
}
