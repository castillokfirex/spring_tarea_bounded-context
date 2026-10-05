package com.example.tarea.domain.aimodel.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;

public class AiModelNotFoundException extends ResourceNotFoundException {

    public AiModelNotFoundException(AiModelId id) {
        super("AiModel with id '" + id.value() + "' was not found");
    }
}
