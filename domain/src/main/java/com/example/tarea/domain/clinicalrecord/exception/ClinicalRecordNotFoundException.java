package com.example.tarea.domain.clinicalrecord.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public class ClinicalRecordNotFoundException extends ResourceNotFoundException {

    public ClinicalRecordNotFoundException(ClinicalRecordId id) {
        super("ClinicalRecord with id '" + id.value() + "' was not found");
    }
}
