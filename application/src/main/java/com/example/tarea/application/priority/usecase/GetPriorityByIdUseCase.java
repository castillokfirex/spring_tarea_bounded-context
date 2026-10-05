package com.example.tarea.application.priority.usecase;

import com.example.tarea.application.priority.dto.PriorityResponse;
import com.example.tarea.domain.priority.exception.PriorityNotFoundException;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;
import com.example.tarea.domain.priority.port.repository.PriorityRepository;

public class GetPriorityByIdUseCase {

    private final PriorityRepository repository;

    public GetPriorityByIdUseCase(PriorityRepository repository) {
        this.repository = repository;
    }

    public PriorityResponse execute(PriorityId id) {
        return repository.findById(id)
                .map(PriorityResponse::fromDomain)
                .orElseThrow(() -> new PriorityNotFoundException(id));
    }
}
