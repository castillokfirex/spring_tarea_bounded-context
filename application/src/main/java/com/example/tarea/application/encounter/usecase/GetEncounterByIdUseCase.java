package com.example.tarea.application.encounter.usecase;

import com.example.tarea.application.encounter.dto.EncounterResponse;
import com.example.tarea.domain.encounter.exception.EncounterNotFoundException;
import com.example.tarea.domain.encounter.model.valueobject.EncounterId;
import com.example.tarea.domain.encounter.port.repository.EncounterRepository;

public class GetEncounterByIdUseCase {

    private final EncounterRepository repository;

    public GetEncounterByIdUseCase(EncounterRepository repository) {
        this.repository = repository;
    }

    public EncounterResponse execute(EncounterId id) {
        return repository.findById(id)
                .map(EncounterResponse::fromDomain)
                .orElseThrow(() -> new EncounterNotFoundException(id));
    }
}
