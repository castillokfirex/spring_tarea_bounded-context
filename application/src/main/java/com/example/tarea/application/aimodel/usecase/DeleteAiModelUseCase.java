package com.example.tarea.application.aimodel.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.aimodel.exception.AiModelNotFoundException;
import com.example.tarea.domain.aimodel.model.aggregate.AiModel;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;
import com.example.tarea.domain.aimodel.port.repository.AiModelRepository;

public class DeleteAiModelUseCase {

    private final AiModelRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteAiModelUseCase(AiModelRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(AiModelId id) {

        AiModel aiModel = repository.findById(id)
                .orElseThrow(() -> new AiModelNotFoundException(id));

        aiModel.delete();
        repository.delete(aiModel);

        eventPublisher.publish(aiModel.domainEvents());
        aiModel.clearDomainEvents();
    }
}
