package com.example.tarea.domain.chatconversationaisetting.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public class ChatConversationAiSettingNotFoundException extends ResourceNotFoundException {

    public ChatConversationAiSettingNotFoundException(ChatConversationAiSettingId id) {
        super("ChatConversationAiSetting with id '" + id.value() + "' was not found");
    }
}
