package com.example.tarea.domain.professionaltype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class ProfessionalTypeNotFoundException extends ResourceNotFoundException {

    public ProfessionalTypeNotFoundException(ProfessionalTypeId id) {
        super("ProfessionalType with id '" + id.value() + "' was not found");
    }
}
