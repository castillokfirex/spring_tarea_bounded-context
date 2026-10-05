package com.example.tarea.domain.stateregion.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;

public class StateRegionNotFoundException extends ResourceNotFoundException {

    public StateRegionNotFoundException(StateRegionId id) {
        super("StateRegion with id '" + id.value() + "' was not found");
    }
}
