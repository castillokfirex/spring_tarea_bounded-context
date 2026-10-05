package com.example.tarea.application.escalationstatus.usecase;

import java.util.List;

import com.example.tarea.application.escalationstatus.dto.EscalationStatusResponse;
import com.example.tarea.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class ListEscalationStatusUseCase {

    private final EscalationStatusRepository repository;

    public ListEscalationStatusUseCase(EscalationStatusRepository repository) {
        this.repository = repository;
    }

    public List<EscalationStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(EscalationStatusResponse::fromDomain)
                .toList();
    }
}
