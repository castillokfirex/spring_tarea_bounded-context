package com.example.tarea.domain.emailcontact.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;

public record EmailContactRegisteredEvent(
        EmailContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
