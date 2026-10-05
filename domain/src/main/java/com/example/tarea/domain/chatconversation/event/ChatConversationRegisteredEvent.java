package com.example.tarea.domain.chatconversation.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;

public record ChatConversationRegisteredEvent(
        ChatConversationId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
