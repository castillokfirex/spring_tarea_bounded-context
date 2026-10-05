package com.example.tarea.domain.professionalstudy.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public class ProfessionalStudyNotFoundException extends ResourceNotFoundException {

    public ProfessionalStudyNotFoundException(ProfessionalStudyId id) {
        super("ProfessionalStudy with id '" + id.value() + "' was not found");
    }
}
