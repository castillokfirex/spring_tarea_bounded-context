package com.example.tarea.application.stateregion.usecase;

import com.example.tarea.application.stateregion.dto.StateRegionResponse;
import com.example.tarea.domain.stateregion.exception.StateRegionNotFoundException;
import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;
import com.example.tarea.domain.stateregion.port.repository.StateRegionRepository;

public class GetStateRegionByIdUseCase {

    private final StateRegionRepository repository;

    public GetStateRegionByIdUseCase(StateRegionRepository repository) {
        this.repository = repository;
    }

    public StateRegionResponse execute(StateRegionId id) {
        return repository.findById(id)
                .map(StateRegionResponse::fromDomain)
                .orElseThrow(() -> new StateRegionNotFoundException(id));
    }
}
