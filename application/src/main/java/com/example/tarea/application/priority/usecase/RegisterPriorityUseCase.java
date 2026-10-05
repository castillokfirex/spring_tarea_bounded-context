package com.example.tarea.application.priority.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.priority.command.RegisterPriorityCommand;
import com.example.tarea.application.priority.dto.PriorityResponse;
import com.example.tarea.domain.priority.model.aggregate.Priority;
import com.example.tarea.domain.priority.port.repository.PriorityRepository;

public class RegisterPriorityUseCase {

    private final PriorityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterPriorityUseCase(PriorityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PriorityResponse execute(RegisterPriorityCommand command) {

        Priority priority = Priority.register(
                command.namePriority());

        Priority saved = repository.save(priority);

        eventPublisher.publish(priority.domainEvents());
        priority.clearDomainEvents();

        return PriorityResponse.fromDomain(saved);
    }
}
