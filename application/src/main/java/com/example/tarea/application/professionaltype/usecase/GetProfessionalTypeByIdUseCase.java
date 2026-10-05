package com.example.tarea.application.professionaltype.usecase;

import com.example.tarea.application.professionaltype.dto.ProfessionalTypeResponse;
import com.example.tarea.domain.professionaltype.exception.ProfessionalTypeNotFoundException;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.example.tarea.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class GetProfessionalTypeByIdUseCase {

    private final ProfessionalTypeRepository repository;

    public GetProfessionalTypeByIdUseCase(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    public ProfessionalTypeResponse execute(ProfessionalTypeId id) {
        return repository.findById(id)
                .map(ProfessionalTypeResponse::fromDomain)
                .orElseThrow(() -> new ProfessionalTypeNotFoundException(id));
    }
}
