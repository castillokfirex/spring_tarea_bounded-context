package com.example.tarea.application.airunstatus.usecase;

import com.example.tarea.application.airunstatus.dto.AiRunStatusResponse;
import com.example.tarea.domain.airunstatus.exception.AiRunStatusNotFoundException;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.example.tarea.domain.airunstatus.port.repository.AiRunStatusRepository;

public class GetAiRunStatusByIdUseCase {

    private final AiRunStatusRepository repository;

    public GetAiRunStatusByIdUseCase(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    public AiRunStatusResponse execute(AiRunStatusId id) {
        return repository.findById(id)
                .map(AiRunStatusResponse::fromDomain)
                .orElseThrow(() -> new AiRunStatusNotFoundException(id));
    }
}
