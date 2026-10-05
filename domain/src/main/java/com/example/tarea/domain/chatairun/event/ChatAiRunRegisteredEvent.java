package com.example.tarea.domain.chatairun.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;

public record ChatAiRunRegisteredEvent(
        ChatAiRunId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
