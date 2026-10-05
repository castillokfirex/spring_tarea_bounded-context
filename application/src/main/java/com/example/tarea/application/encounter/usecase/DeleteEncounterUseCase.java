package com.example.tarea.application.encounter.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.encounter.exception.EncounterNotFoundException;
import com.example.tarea.domain.encounter.model.aggregate.Encounter;
import com.example.tarea.domain.encounter.model.valueobject.EncounterId;
import com.example.tarea.domain.encounter.port.repository.EncounterRepository;

public class DeleteEncounterUseCase {

    private final EncounterRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteEncounterUseCase(EncounterRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(EncounterId id) {

        Encounter encounter = repository.findById(id)
                .orElseThrow(() -> new EncounterNotFoundException(id));

        encounter.delete();
        repository.delete(encounter);

        eventPublisher.publish(encounter.domainEvents());
        encounter.clearDomainEvents();
    }
}
