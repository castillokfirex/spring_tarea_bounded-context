package com.example.tarea.domain.aimodel.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;

public record AiModelRegisteredEvent(
        AiModelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
