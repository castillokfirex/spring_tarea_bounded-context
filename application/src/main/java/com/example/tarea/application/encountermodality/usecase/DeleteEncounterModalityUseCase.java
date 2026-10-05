package com.example.tarea.application.encountermodality.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.encountermodality.exception.EncounterModalityNotFoundException;
import com.example.tarea.domain.encountermodality.model.aggregate.EncounterModality;
import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.example.tarea.domain.encountermodality.port.repository.EncounterModalityRepository;

public class DeleteEncounterModalityUseCase {

    private final EncounterModalityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteEncounterModalityUseCase(EncounterModalityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(EncounterModalityId id) {

        EncounterModality encounterModality = repository.findById(id)
                .orElseThrow(() -> new EncounterModalityNotFoundException(id));

        encounterModality.delete();
        repository.delete(encounterModality);

        eventPublisher.publish(encounterModality.domainEvents());
        encounterModality.clearDomainEvents();
    }
}
