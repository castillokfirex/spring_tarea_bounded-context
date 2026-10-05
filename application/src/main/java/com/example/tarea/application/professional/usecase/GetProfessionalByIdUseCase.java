package com.example.tarea.application.professional.usecase;

import com.example.tarea.application.professional.dto.ProfessionalResponse;
import com.example.tarea.domain.professional.exception.ProfessionalNotFoundException;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;
import com.example.tarea.domain.professional.port.repository.ProfessionalRepository;

public class GetProfessionalByIdUseCase {

    private final ProfessionalRepository repository;

    public GetProfessionalByIdUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public ProfessionalResponse execute(ProfessionalId id) {
        return repository.findById(id)
                .map(ProfessionalResponse::fromDomain)
                .orElseThrow(() -> new ProfessionalNotFoundException(id));
    }
}
