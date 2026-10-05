package com.example.tarea.application.providermodelai.command;

import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;

public record UpdateProviderModelAiCommand(
        ProviderModelAiId id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        Boolean isActive
) {
}
