package com.example.tarea.application.phonecontact.command;

import java.util.UUID;

import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;

public record UpdatePhoneContactCommand(
        PhoneContactId id,
        UUID contactId,
        String phone,
        String notes
) {
}
