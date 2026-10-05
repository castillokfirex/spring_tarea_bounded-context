package com.example.tarea.domain.chatmessage.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;

public record ChatMessageDeletedEvent(
        ChatMessageId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
