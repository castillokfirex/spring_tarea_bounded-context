package com.example.tarea.domain.conversationstatus.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;

public class ConversationStatusNotFoundException extends ResourceNotFoundException {

    public ConversationStatusNotFoundException(ConversationStatusId id) {
        super("ConversationStatus with id '" + id.value() + "' was not found");
    }
}
