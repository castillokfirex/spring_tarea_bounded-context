package com.example.tarea.domain.clinicalnote.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public class ClinicalNoteNotFoundException extends ResourceNotFoundException {

    public ClinicalNoteNotFoundException(ClinicalNoteId id) {
        super("ClinicalNote with id '" + id.value() + "' was not found");
    }
}
