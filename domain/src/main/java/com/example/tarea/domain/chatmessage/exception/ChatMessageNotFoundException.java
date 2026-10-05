package com.example.tarea.domain.chatmessage.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;

public class ChatMessageNotFoundException extends ResourceNotFoundException {

    public ChatMessageNotFoundException(ChatMessageId id) {
        super("ChatMessage with id '" + id.value() + "' was not found");
    }
}
