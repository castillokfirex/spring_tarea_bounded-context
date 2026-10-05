package com.example.tarea.application.encountermodality.usecase;

import java.util.List;

import com.example.tarea.application.encountermodality.dto.EncounterModalityResponse;
import com.example.tarea.domain.encountermodality.port.repository.EncounterModalityRepository;

public class ListEncounterModalityUseCase {

    private final EncounterModalityRepository repository;

    public ListEncounterModalityUseCase(EncounterModalityRepository repository) {
        this.repository = repository;
    }

    public List<EncounterModalityResponse> execute() {
        return repository.findAll()
                .stream()
                .map(EncounterModalityResponse::fromDomain)
                .toList();
    }
}
