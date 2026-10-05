package com.example.tarea.application.aimodel.usecase;

import java.util.List;

import com.example.tarea.application.aimodel.dto.AiModelResponse;
import com.example.tarea.domain.aimodel.port.repository.AiModelRepository;

public class ListAiModelUseCase {

    private final AiModelRepository repository;

    public ListAiModelUseCase(AiModelRepository repository) {
        this.repository = repository;
    }

    public List<AiModelResponse> execute() {
        return repository.findAll()
                .stream()
                .map(AiModelResponse::fromDomain)
                .toList();
    }
}
