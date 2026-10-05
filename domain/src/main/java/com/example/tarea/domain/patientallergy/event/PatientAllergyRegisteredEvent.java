package com.example.tarea.domain.patientallergy.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;

public record PatientAllergyRegisteredEvent(
        PatientAllergyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
