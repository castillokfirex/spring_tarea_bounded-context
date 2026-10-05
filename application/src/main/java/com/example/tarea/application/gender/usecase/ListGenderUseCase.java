package com.example.tarea.application.gender.usecase;

import java.util.List;

import com.example.tarea.application.gender.dto.GenderResponse;
import com.example.tarea.domain.gender.port.repository.GenderRepository;

public class ListGenderUseCase {

    private final GenderRepository repository;

    public ListGenderUseCase(GenderRepository repository) {
        this.repository = repository;
    }

    public List<GenderResponse> execute() {
        return repository.findAll()
                .stream()
                .map(GenderResponse::fromDomain)
                .toList();
    }
}
