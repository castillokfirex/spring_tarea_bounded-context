package com.example.tarea.domain.chatconversationaisetting.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public record ChatConversationAiSettingDeletedEvent(
        ChatConversationAiSettingId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
