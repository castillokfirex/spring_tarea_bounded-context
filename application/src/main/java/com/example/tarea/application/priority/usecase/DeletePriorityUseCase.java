package com.example.tarea.application.priority.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.priority.exception.PriorityNotFoundException;
import com.example.tarea.domain.priority.model.aggregate.Priority;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;
import com.example.tarea.domain.priority.port.repository.PriorityRepository;

public class DeletePriorityUseCase {

    private final PriorityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeletePriorityUseCase(PriorityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(PriorityId id) {

        Priority priority = repository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundException(id));

        priority.delete();
        repository.delete(priority);

        eventPublisher.publish(priority.domainEvents());
        priority.clearDomainEvents();
    }
}
