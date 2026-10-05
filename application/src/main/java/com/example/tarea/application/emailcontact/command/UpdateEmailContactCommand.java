package com.example.tarea.application.emailcontact.command;

import java.util.UUID;

import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;

public record UpdateEmailContactCommand(
        EmailContactId id,
        UUID contactId,
        String email,
        String notes
) {
}
