package com.example.tarea.domain.citymunicipality.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public record CityMunicipalityUpdatedEvent(
        CityMunicipalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
