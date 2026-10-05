package com.example.tarea.application.encounter.usecase;

import java.util.List;

import com.example.tarea.application.encounter.dto.EncounterResponse;
import com.example.tarea.domain.encounter.port.repository.EncounterRepository;

public class ListEncounterUseCase {

    private final EncounterRepository repository;

    public ListEncounterUseCase(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<EncounterResponse> execute() {
        return repository.findAll()
                .stream()
                .map(EncounterResponse::fromDomain)
                .toList();
    }
}
