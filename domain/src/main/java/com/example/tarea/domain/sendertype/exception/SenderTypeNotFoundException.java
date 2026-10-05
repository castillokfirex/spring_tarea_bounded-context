package com.example.tarea.domain.sendertype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.sendertype.model.valueobject.SenderTypeId;

public class SenderTypeNotFoundException extends ResourceNotFoundException {

    public SenderTypeNotFoundException(SenderTypeId id) {
        super("SenderType with id '" + id.value() + "' was not found");
    }
}
