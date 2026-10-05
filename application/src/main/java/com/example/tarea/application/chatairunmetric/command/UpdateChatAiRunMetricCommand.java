package com.example.tarea.application.chatairunmetric.command;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public record UpdateChatAiRunMetricCommand(
        ChatAiRunMetricId id,
        UUID aiRunId,
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens,
        BigDecimal cost
) {
}
