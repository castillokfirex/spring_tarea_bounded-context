package com.example.tarea.application.encounterstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.encounterstatus.exception.EncounterStatusNotFoundException;
import com.example.tarea.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.example.tarea.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.example.tarea.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class DeleteEncounterStatusUseCase {

    private final EncounterStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteEncounterStatusUseCase(EncounterStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(EncounterStatusId id) {

        EncounterStatus encounterStatus = repository.findById(id)
                .orElseThrow(() -> new EncounterStatusNotFoundException(id));

        encounterStatus.delete();
        repository.delete(encounterStatus);

        eventPublisher.publish(encounterStatus.domainEvents());
        encounterStatus.clearDomainEvents();
    }
}
