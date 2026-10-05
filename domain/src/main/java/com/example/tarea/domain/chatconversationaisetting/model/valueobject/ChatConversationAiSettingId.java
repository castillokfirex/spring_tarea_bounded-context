package com.example.tarea.domain.chatconversationaisetting.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatConversationAiSetting (value object).
 */
public record ChatConversationAiSettingId(UUID value) {

    public ChatConversationAiSettingId {
        if (value == null) {
            throw new DomainValidationException("ChatConversationAiSettingId value must not be null");
        }
    }

    public static ChatConversationAiSettingId generate() {
        return new ChatConversationAiSettingId(UUID.randomUUID());
    }

    public static ChatConversationAiSettingId of(UUID value) {
        return new ChatConversationAiSettingId(value);
    }
}
