package com.example.tarea.domain.chatconversation.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;

public class ChatConversationNotFoundException extends ResourceNotFoundException {

    public ChatConversationNotFoundException(ChatConversationId id) {
        super("ChatConversation with id '" + id.value() + "' was not found");
    }
}
