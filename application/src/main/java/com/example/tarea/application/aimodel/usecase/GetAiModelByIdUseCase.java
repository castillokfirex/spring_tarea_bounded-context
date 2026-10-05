package com.example.tarea.application.aimodel.usecase;

import com.example.tarea.application.aimodel.dto.AiModelResponse;
import com.example.tarea.domain.aimodel.exception.AiModelNotFoundException;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;
import com.example.tarea.domain.aimodel.port.repository.AiModelRepository;

public class GetAiModelByIdUseCase {

    private final AiModelRepository repository;

    public GetAiModelByIdUseCase(AiModelRepository repository) {
        this.repository = repository;
    }

    public AiModelResponse execute(AiModelId id) {
        return repository.findById(id)
                .map(AiModelResponse::fromDomain)
                .orElseThrow(() -> new AiModelNotFoundException(id));
    }
}
