package com.example.tarea.application.gender.usecase;

import com.example.tarea.application.gender.dto.GenderResponse;
import com.example.tarea.domain.gender.exception.GenderNotFoundException;
import com.example.tarea.domain.gender.model.valueobject.GenderId;
import com.example.tarea.domain.gender.port.repository.GenderRepository;

public class GetGenderByIdUseCase {

    private final GenderRepository repository;

    public GetGenderByIdUseCase(GenderRepository repository) {
        this.repository = repository;
    }

    public GenderResponse execute(GenderId id) {
        return repository.findById(id)
                .map(GenderResponse::fromDomain)
                .orElseThrow(() -> new GenderNotFoundException(id));
    }
}
