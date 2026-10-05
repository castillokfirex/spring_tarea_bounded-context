package com.example.tarea.domain.consenttype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;

public class ConsentTypeNotFoundException extends ResourceNotFoundException {

    public ConsentTypeNotFoundException(ConsentTypeId id) {
        super("ConsentType with id '" + id.value() + "' was not found");
    }
}
