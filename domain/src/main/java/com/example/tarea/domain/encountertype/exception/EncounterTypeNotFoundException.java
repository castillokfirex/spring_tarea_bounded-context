package com.example.tarea.domain.encountertype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;

public class EncounterTypeNotFoundException extends ResourceNotFoundException {

    public EncounterTypeNotFoundException(EncounterTypeId id) {
        super("EncounterType with id '" + id.value() + "' was not found");
    }
}
