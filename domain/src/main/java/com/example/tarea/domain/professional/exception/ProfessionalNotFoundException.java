package com.example.tarea.domain.professional.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;

public class ProfessionalNotFoundException extends ResourceNotFoundException {

    public ProfessionalNotFoundException(ProfessionalId id) {
        super("Professional with id '" + id.value() + "' was not found");
    }
}
