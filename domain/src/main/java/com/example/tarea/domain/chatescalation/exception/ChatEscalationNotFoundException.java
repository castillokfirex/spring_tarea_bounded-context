package com.example.tarea.domain.chatescalation.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;

public class ChatEscalationNotFoundException extends ResourceNotFoundException {

    public ChatEscalationNotFoundException(ChatEscalationId id) {
        super("ChatEscalation with id '" + id.value() + "' was not found");
    }
}
