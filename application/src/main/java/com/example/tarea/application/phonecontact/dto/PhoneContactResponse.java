package com.example.tarea.application.phonecontact.dto;

import java.util.UUID;

import com.example.tarea.domain.phonecontact.model.aggregate.PhoneContact;

public record PhoneContactResponse(
        UUID id,
        UUID contactId,
        String phone,
        String notes) {

    public static PhoneContactResponse fromDomain(PhoneContact aggregate) {
        return new PhoneContactResponse(
                aggregate.id().value(),
                aggregate.contactId(),
                aggregate.phone(),
                aggregate.notes());
    }
}
