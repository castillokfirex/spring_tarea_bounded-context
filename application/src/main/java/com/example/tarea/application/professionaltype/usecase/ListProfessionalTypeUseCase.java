package com.example.tarea.application.professionaltype.usecase;

import java.util.List;

import com.example.tarea.application.professionaltype.dto.ProfessionalTypeResponse;
import com.example.tarea.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class ListProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;

    public ListProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    public List<ProfessionalTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ProfessionalTypeResponse::fromDomain)
                .toList();
    }
}
