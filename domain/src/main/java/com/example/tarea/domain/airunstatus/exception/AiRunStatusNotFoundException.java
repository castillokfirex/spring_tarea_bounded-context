package com.example.tarea.domain.airunstatus.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;

public class AiRunStatusNotFoundException extends ResourceNotFoundException {

    public AiRunStatusNotFoundException(AiRunStatusId id) {
        super("AiRunStatus with id '" + id.value() + "' was not found");
    }
}
