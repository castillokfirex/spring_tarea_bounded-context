package com.example.tarea.domain.encounter.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.encounter.model.valueobject.EncounterId;

public class EncounterNotFoundException extends ResourceNotFoundException {

    public EncounterNotFoundException(EncounterId id) {
        super("Encounter with id '" + id.value() + "' was not found");
    }
}
