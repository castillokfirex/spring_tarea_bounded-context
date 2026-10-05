package com.example.tarea.application.encounterstatus.usecase;

import com.example.tarea.application.encounterstatus.dto.EncounterStatusResponse;
import com.example.tarea.domain.encounterstatus.exception.EncounterStatusNotFoundException;
import com.example.tarea.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.example.tarea.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class GetEncounterStatusByIdUseCase {

    private final EncounterStatusRepository repository;

    public GetEncounterStatusByIdUseCase(EncounterStatusRepository repository) {
        this.repository = repository;
    }

    public EncounterStatusResponse execute(EncounterStatusId id) {
        return repository.findById(id)
                .map(EncounterStatusResponse::fromDomain)
                .orElseThrow(() -> new EncounterStatusNotFoundException(id));
    }
}
