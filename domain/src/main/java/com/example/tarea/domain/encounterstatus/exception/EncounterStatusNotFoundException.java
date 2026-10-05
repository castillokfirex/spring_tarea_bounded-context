package com.example.tarea.domain.encounterstatus.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.encounterstatus.model.valueobject.EncounterStatusId;

public class EncounterStatusNotFoundException extends ResourceNotFoundException {

    public EncounterStatusNotFoundException(EncounterStatusId id) {
        super("EncounterStatus with id '" + id.value() + "' was not found");
    }
}
