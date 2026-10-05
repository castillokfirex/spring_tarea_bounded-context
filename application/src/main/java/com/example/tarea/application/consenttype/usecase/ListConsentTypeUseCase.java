package com.example.tarea.application.consenttype.usecase;

import java.util.List;

import com.example.tarea.application.consenttype.dto.ConsentTypeResponse;
import com.example.tarea.domain.consenttype.port.repository.ConsentTypeRepository;

public class ListConsentTypeUseCase {

    private final ConsentTypeRepository repository;

    public ListConsentTypeUseCase(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    public List<ConsentTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ConsentTypeResponse::fromDomain)
                .toList();
    }
}
