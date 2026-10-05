package com.example.tarea.application.stateregion.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.stateregion.exception.StateRegionNotFoundException;
import com.example.tarea.domain.stateregion.model.aggregate.StateRegion;
import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;
import com.example.tarea.domain.stateregion.port.repository.StateRegionRepository;

public class DeleteStateRegionUseCase {

    private final StateRegionRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteStateRegionUseCase(StateRegionRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(StateRegionId id) {

        StateRegion stateRegion = repository.findById(id)
                .orElseThrow(() -> new StateRegionNotFoundException(id));

        stateRegion.delete();
        repository.delete(stateRegion);

        eventPublisher.publish(stateRegion.domainEvents());
        stateRegion.clearDomainEvents();
    }
}
