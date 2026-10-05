package com.example.tarea.domain.country.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.country.model.valueobject.CountryId;

public class CountryNotFoundException extends ResourceNotFoundException {

    public CountryNotFoundException(CountryId id) {
        super("Country with id '" + id.value() + "' was not found");
    }
}
