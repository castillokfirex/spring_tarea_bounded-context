package com.example.tarea.domain.gender.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.gender.model.valueobject.GenderId;

public class GenderNotFoundException extends ResourceNotFoundException {

    public GenderNotFoundException(GenderId id) {
        super("Gender with id '" + id.value() + "' was not found");
    }
}
