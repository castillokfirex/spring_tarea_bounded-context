package com.example.tarea.domain.chatairunmetric.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatAiRunMetric (value object).
 */
public record ChatAiRunMetricId(UUID value) {

    public ChatAiRunMetricId {
        if (value == null) {
            throw new DomainValidationException("ChatAiRunMetricId value must not be null");
        }
    }

    public static ChatAiRunMetricId generate() {
        return new ChatAiRunMetricId(UUID.randomUUID());
    }

    public static ChatAiRunMetricId of(UUID value) {
        return new ChatAiRunMetricId(value);
    }
}
