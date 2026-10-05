package com.example.tarea.infrastructure.chatairunmetric.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateChatAiRunMetricRequest(
        @NotNull UUID aiRunId,
        @NotNull Integer promptTokens,
        @NotNull Integer completionTokens,
        @NotNull Integer totalTokens,
        @NotNull BigDecimal cost
) {
}
