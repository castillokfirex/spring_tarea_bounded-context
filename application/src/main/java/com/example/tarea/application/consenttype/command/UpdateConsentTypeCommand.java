package com.example.tarea.application.consenttype.command;

import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;

public record UpdateConsentTypeCommand(
        ConsentTypeId id,
        String code,
        String name,
        Boolean active,
        String description
) {
}
