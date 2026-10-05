package com.example.tarea.domain.clinicalrecordstatus.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public class ClinicalRecordStatusNotFoundException extends ResourceNotFoundException {

    public ClinicalRecordStatusNotFoundException(ClinicalRecordStatusId id) {
        super("ClinicalRecordStatus with id '" + id.value() + "' was not found");
    }
}
