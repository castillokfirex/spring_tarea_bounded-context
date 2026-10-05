package com.example.tarea.application.priority.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.priority.command.UpdatePriorityCommand;
import com.example.tarea.application.priority.dto.PriorityResponse;
import com.example.tarea.domain.priority.exception.PriorityNotFoundException;
import com.example.tarea.domain.priority.model.aggregate.Priority;
import com.example.tarea.domain.priority.port.repository.PriorityRepository;

public class UpdatePriorityUseCase {

    private final PriorityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdatePriorityUseCase(PriorityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PriorityResponse execute(UpdatePriorityCommand command) {

        Priority priority = repository.findById(command.id())
                .orElseThrow(() -> new PriorityNotFoundException(command.id()));

        priority.update(
                command.namePriority());

        Priority saved = repository.save(priority);

        eventPublisher.publish(priority.domainEvents());
        priority.clearDomainEvents();

        return PriorityResponse.fromDomain(saved);
    }
}
