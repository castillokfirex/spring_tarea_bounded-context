package com.example.tarea.domain.escalationstatus.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class EscalationStatusNotFoundException extends ResourceNotFoundException {

    public EscalationStatusNotFoundException(EscalationStatusId id) {
        super("EscalationStatus with id '" + id.value() + "' was not found");
    }
}
