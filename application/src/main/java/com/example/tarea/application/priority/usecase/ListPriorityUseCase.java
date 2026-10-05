package com.example.tarea.application.priority.usecase;

import java.util.List;

import com.example.tarea.application.priority.dto.PriorityResponse;
import com.example.tarea.domain.priority.port.repository.PriorityRepository;

public class ListPriorityUseCase {

    private final PriorityRepository repository;

    public ListPriorityUseCase(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<PriorityResponse> execute() {
        return repository.findAll()
                .stream()
                .map(PriorityResponse::fromDomain)
                .toList();
    }
}
