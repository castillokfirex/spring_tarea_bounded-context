package com.example.tarea.domain.messagetype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;

public class MessageTypeNotFoundException extends ResourceNotFoundException {

    public MessageTypeNotFoundException(MessageTypeId id) {
        super("MessageType with id '" + id.value() + "' was not found");
    }
}
