package com.example.tarea.application.airunstatus.usecase;

import com.example.tarea.application.airunstatus.command.UpdateAiRunStatusCommand;
import com.example.tarea.application.airunstatus.dto.AiRunStatusResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.airunstatus.exception.AiRunStatusNotFoundException;
import com.example.tarea.domain.airunstatus.model.aggregate.AiRunStatus;
import com.example.tarea.domain.airunstatus.port.repository.AiRunStatusRepository;

public class UpdateAiRunStatusUseCase {

    private final AiRunStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateAiRunStatusUseCase(AiRunStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public AiRunStatusResponse execute(UpdateAiRunStatusCommand command) {

        AiRunStatus aiRunStatus = repository.findById(command.id())
                .orElseThrow(() -> new AiRunStatusNotFoundException(command.id()));

        aiRunStatus.update(
                command.nameStatus());

        AiRunStatus saved = repository.save(aiRunStatus);

        eventPublisher.publish(aiRunStatus.domainEvents());
        aiRunStatus.clearDomainEvents();

        return AiRunStatusResponse.fromDomain(saved);
    }
}
