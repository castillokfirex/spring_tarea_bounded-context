package com.example.tarea.application.airunstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.airunstatus.exception.AiRunStatusNotFoundException;
import com.example.tarea.domain.airunstatus.model.aggregate.AiRunStatus;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.example.tarea.domain.airunstatus.port.repository.AiRunStatusRepository;

public class DeleteAiRunStatusUseCase {

    private final AiRunStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteAiRunStatusUseCase(AiRunStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(AiRunStatusId id) {

        AiRunStatus aiRunStatus = repository.findById(id)
                .orElseThrow(() -> new AiRunStatusNotFoundException(id));

        aiRunStatus.delete();
        repository.delete(aiRunStatus);

        eventPublisher.publish(aiRunStatus.domainEvents());
        aiRunStatus.clearDomainEvents();
    }
}
