package com.example.tarea.domain.patientcontact.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;

public record PatientContactRegisteredEvent(
        PatientContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
