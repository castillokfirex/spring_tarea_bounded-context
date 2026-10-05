package com.example.tarea.application.airunstatus.usecase;

import java.util.List;

import com.example.tarea.application.airunstatus.dto.AiRunStatusResponse;
import com.example.tarea.domain.airunstatus.port.repository.AiRunStatusRepository;

public class ListAiRunStatusUseCase {

    private final AiRunStatusRepository repository;

    public ListAiRunStatusUseCase(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    public List<AiRunStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(AiRunStatusResponse::fromDomain)
                .toList();
    }
}
