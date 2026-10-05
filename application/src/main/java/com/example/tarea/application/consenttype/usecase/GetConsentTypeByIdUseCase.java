package com.example.tarea.application.consenttype.usecase;

import com.example.tarea.application.consenttype.dto.ConsentTypeResponse;
import com.example.tarea.domain.consenttype.exception.ConsentTypeNotFoundException;
import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;
import com.example.tarea.domain.consenttype.port.repository.ConsentTypeRepository;

public class GetConsentTypeByIdUseCase {

    private final ConsentTypeRepository repository;

    public GetConsentTypeByIdUseCase(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    public ConsentTypeResponse execute(ConsentTypeId id) {
        return repository.findById(id)
                .map(ConsentTypeResponse::fromDomain)
                .orElseThrow(() -> new ConsentTypeNotFoundException(id));
    }
}
