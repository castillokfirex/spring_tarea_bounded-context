package com.example.tarea.infrastructure.aimodel.adapters.in.rest.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAiModelRequest(
        @NotBlank @Size(max = 50) String providerModelId,
        @NotBlank @Size(max = 100) String nameModel,
        @NotBlank @Size(max = 170) String modelKey,
        @NotNull BigDecimal inputTokenPrice,
        @NotNull BigDecimal outputTokenPrice,
        @NotNull Integer maxTokens,
        @NotNull Integer contextWindow,
        @NotNull Boolean isActive
) {
}
