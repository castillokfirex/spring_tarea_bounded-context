package com.example.tarea.domain.providermodelai.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;

public record ProviderModelAiRegisteredEvent(
        ProviderModelAiId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
