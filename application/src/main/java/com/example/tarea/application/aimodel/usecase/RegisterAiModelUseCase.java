package com.example.tarea.application.aimodel.usecase;

import com.example.tarea.application.aimodel.command.RegisterAiModelCommand;
import com.example.tarea.application.aimodel.dto.AiModelResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.aimodel.model.aggregate.AiModel;
import com.example.tarea.domain.aimodel.port.repository.AiModelRepository;

public class RegisterAiModelUseCase {

    private final AiModelRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterAiModelUseCase(AiModelRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public AiModelResponse execute(RegisterAiModelCommand command) {

        AiModel aiModel = AiModel.register(
                command.providerModelId(),
                command.nameModel(),
                command.modelKey(),
                command.inputTokenPrice(),
                command.outputTokenPrice(),
                command.maxTokens(),
                command.contextWindow(),
                command.isActive());

        AiModel saved = repository.save(aiModel);

        eventPublisher.publish(aiModel.domainEvents());
        aiModel.clearDomainEvents();

        return AiModelResponse.fromDomain(saved);
    }
}
