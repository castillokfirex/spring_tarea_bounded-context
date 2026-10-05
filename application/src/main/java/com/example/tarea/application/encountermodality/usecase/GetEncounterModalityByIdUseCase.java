package com.example.tarea.application.encountermodality.usecase;

import com.example.tarea.application.encountermodality.dto.EncounterModalityResponse;
import com.example.tarea.domain.encountermodality.exception.EncounterModalityNotFoundException;
import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.example.tarea.domain.encountermodality.port.repository.EncounterModalityRepository;

public class GetEncounterModalityByIdUseCase {

    private final EncounterModalityRepository repository;

    public GetEncounterModalityByIdUseCase(EncounterModalityRepository repository) {
        this.repository = repository;
    }

    public EncounterModalityResponse execute(EncounterModalityId id) {
        return repository.findById(id)
                .map(EncounterModalityResponse::fromDomain)
                .orElseThrow(() -> new EncounterModalityNotFoundException(id));
    }
}
