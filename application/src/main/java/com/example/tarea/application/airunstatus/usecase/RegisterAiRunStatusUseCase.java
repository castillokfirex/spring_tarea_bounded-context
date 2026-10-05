package com.example.tarea.application.airunstatus.usecase;

import com.example.tarea.application.airunstatus.command.RegisterAiRunStatusCommand;
import com.example.tarea.application.airunstatus.dto.AiRunStatusResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.airunstatus.model.aggregate.AiRunStatus;
import com.example.tarea.domain.airunstatus.port.repository.AiRunStatusRepository;

public class RegisterAiRunStatusUseCase {

    private final AiRunStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterAiRunStatusUseCase(AiRunStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public AiRunStatusResponse execute(RegisterAiRunStatusCommand command) {

        AiRunStatus aiRunStatus = AiRunStatus.register(
                command.nameStatus());

        AiRunStatus saved = repository.save(aiRunStatus);

        eventPublisher.publish(aiRunStatus.domainEvents());
        aiRunStatus.clearDomainEvents();

        return AiRunStatusResponse.fromDomain(saved);
    }
}
