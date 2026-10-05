package com.example.tarea.application.contact.command;

import java.util.UUID;

import com.example.tarea.domain.contact.model.valueobject.ContactId;

public record UpdateContactCommand(
        ContactId id,
        String fullName,
        String email,
        String notes,
        UUID cityId,
        UUID updatedBy
) {
}
