package com.example.tarea.application.encountertype.usecase;

import com.example.tarea.application.encountertype.dto.EncounterTypeResponse;
import com.example.tarea.domain.encountertype.exception.EncounterTypeNotFoundException;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;
import com.example.tarea.domain.encountertype.port.repository.EncounterTypeRepository;

public class GetEncounterTypeByIdUseCase {

    private final EncounterTypeRepository repository;

    public GetEncounterTypeByIdUseCase(EncounterTypeRepository repository) {
        this.repository = repository;
    }

    public EncounterTypeResponse execute(EncounterTypeId id) {
        return repository.findById(id)
                .map(EncounterTypeResponse::fromDomain)
                .orElseThrow(() -> new EncounterTypeNotFoundException(id));
    }
}
