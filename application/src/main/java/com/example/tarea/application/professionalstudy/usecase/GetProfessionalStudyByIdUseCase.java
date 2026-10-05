package com.example.tarea.application.professionalstudy.usecase;

import com.example.tarea.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.example.tarea.domain.professionalstudy.exception.ProfessionalStudyNotFoundException;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.example.tarea.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class GetProfessionalStudyByIdUseCase {

    private final ProfessionalStudyRepository repository;

    public GetProfessionalStudyByIdUseCase(ProfessionalStudyRepository repository) {
        this.repository = repository;
    }

    public ProfessionalStudyResponse execute(ProfessionalStudyId id) {
        return repository.findById(id)
                .map(ProfessionalStudyResponse::fromDomain)
                .orElseThrow(() -> new ProfessionalStudyNotFoundException(id));
    }
}
