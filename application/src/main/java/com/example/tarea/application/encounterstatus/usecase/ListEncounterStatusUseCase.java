package com.example.tarea.application.encounterstatus.usecase;

import java.util.List;

import com.example.tarea.application.encounterstatus.dto.EncounterStatusResponse;
import com.example.tarea.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class ListEncounterStatusUseCase {

    private final EncounterStatusRepository repository;

    public ListEncounterStatusUseCase(EncounterStatusRepository repository) {
        this.repository = repository;
    }

    public List<EncounterStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(EncounterStatusResponse::fromDomain)
                .toList();
    }
}
