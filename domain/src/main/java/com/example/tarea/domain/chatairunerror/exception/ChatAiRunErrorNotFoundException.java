package com.example.tarea.domain.chatairunerror.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public class ChatAiRunErrorNotFoundException extends ResourceNotFoundException {

    public ChatAiRunErrorNotFoundException(ChatAiRunErrorId id) {
        super("ChatAiRunError with id '" + id.value() + "' was not found");
    }
}
