package com.example.tarea.application.messagetype.command;

import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;

public record UpdateMessageTypeCommand(
        MessageTypeId id,
        String nameType
) {
}
