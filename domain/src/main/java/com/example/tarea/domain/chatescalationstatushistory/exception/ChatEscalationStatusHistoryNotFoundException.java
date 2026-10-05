package com.example.tarea.domain.chatescalationstatushistory.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public class ChatEscalationStatusHistoryNotFoundException extends ResourceNotFoundException {

    public ChatEscalationStatusHistoryNotFoundException(ChatEscalationStatusHistoryId id) {
        super("ChatEscalationStatusHistory with id '" + id.value() + "' was not found");
    }
}
