package com.example.tarea.domain.providermodelai.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ProviderModelAi (value object).
 */
public record ProviderModelAiId(UUID value) {

    public ProviderModelAiId {
        if (value == null) {
            throw new DomainValidationException("ProviderModelAiId value must not be null");
        }
    }

    public static ProviderModelAiId generate() {
        return new ProviderModelAiId(UUID.randomUUID());
    }

    public static ProviderModelAiId of(UUID value) {
        return new ProviderModelAiId(value);
    }
}
