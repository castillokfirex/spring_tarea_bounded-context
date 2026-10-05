package com.example.tarea.application.aimodel.command;

import java.math.BigDecimal;

import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;

public record UpdateAiModelCommand(
        AiModelId id,
        String providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow,
        Boolean isActive
) {
}
