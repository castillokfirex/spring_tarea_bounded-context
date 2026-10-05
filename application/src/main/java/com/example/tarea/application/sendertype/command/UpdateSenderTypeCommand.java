package com.example.tarea.application.sendertype.command;

import com.example.tarea.domain.sendertype.model.valueobject.SenderTypeId;

public record UpdateSenderTypeCommand(
        SenderTypeId id,
        String nameType
) {
}
