package com.example.tarea.domain.phonecontact.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;

public record PhoneContactRegisteredEvent(
        PhoneContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
