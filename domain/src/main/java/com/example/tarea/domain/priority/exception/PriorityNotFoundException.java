package com.example.tarea.domain.priority.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;

public class PriorityNotFoundException extends ResourceNotFoundException {

    public PriorityNotFoundException(PriorityId id) {
        super("Priority with id '" + id.value() + "' was not found");
    }
}
