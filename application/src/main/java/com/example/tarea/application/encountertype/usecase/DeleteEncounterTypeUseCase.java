package com.example.tarea.application.encountertype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.encountertype.exception.EncounterTypeNotFoundException;
import com.example.tarea.domain.encountertype.model.aggregate.EncounterType;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;
import com.example.tarea.domain.encountertype.port.repository.EncounterTypeRepository;

public class DeleteEncounterTypeUseCase {

    private final EncounterTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteEncounterTypeUseCase(EncounterTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(EncounterTypeId id) {

        EncounterType encounterType = repository.findById(id)
                .orElseThrow(() -> new EncounterTypeNotFoundException(id));

        encounterType.delete();
        repository.delete(encounterType);

        eventPublisher.publish(encounterType.domainEvents());
        encounterType.clearDomainEvents();
    }
}
