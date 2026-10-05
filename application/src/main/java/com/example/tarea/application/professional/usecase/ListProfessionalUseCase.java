package com.example.tarea.application.professional.usecase;

import java.util.List;

import com.example.tarea.application.professional.dto.ProfessionalResponse;
import com.example.tarea.domain.professional.port.repository.ProfessionalRepository;

public class ListProfessionalUseCase {

    private final ProfessionalRepository repository;

    public ListProfessionalUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public List<ProfessionalResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ProfessionalResponse::fromDomain)
                .toList();
    }
}
