package com.example.tarea.domain.chatairunerror.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public record ChatAiRunErrorDeletedEvent(
        ChatAiRunErrorId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
