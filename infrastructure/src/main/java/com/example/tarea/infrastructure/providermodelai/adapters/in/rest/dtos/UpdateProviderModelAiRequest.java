package com.example.tarea.infrastructure.providermodelai.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProviderModelAiRequest(
        @NotBlank @Size(max = 100) String nameProviderAi,
        @Size(max = 100) String razonSocial,
        String sitioWeb,
        @NotNull Boolean isActive
) {
}
