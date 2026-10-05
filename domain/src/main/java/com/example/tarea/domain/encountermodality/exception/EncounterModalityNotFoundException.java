package com.example.tarea.domain.encountermodality.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;

public class EncounterModalityNotFoundException extends ResourceNotFoundException {

    public EncounterModalityNotFoundException(EncounterModalityId id) {
        super("EncounterModality with id '" + id.value() + "' was not found");
    }
}
