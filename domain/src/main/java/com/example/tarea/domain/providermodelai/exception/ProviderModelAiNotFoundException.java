package com.example.tarea.domain.providermodelai.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;

public class ProviderModelAiNotFoundException extends ResourceNotFoundException {

    public ProviderModelAiNotFoundException(ProviderModelAiId id) {
        super("ProviderModelAi with id '" + id.value() + "' was not found");
    }
}
