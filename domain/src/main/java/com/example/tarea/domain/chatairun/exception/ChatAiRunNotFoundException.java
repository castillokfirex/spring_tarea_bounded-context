package com.example.tarea.domain.chatairun.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;

public class ChatAiRunNotFoundException extends ResourceNotFoundException {

    public ChatAiRunNotFoundException(ChatAiRunId id) {
        super("ChatAiRun with id '" + id.value() + "' was not found");
    }
}
