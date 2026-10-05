package com.example.tarea.domain.chatairunmetric.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public record ChatAiRunMetricRegisteredEvent(
        ChatAiRunMetricId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
