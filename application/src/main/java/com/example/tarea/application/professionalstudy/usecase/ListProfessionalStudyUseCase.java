package com.example.tarea.application.professionalstudy.usecase;

import java.util.List;

import com.example.tarea.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.example.tarea.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class ListProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;

    public ListProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        this.repository = repository;
    }

    public List<ProfessionalStudyResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ProfessionalStudyResponse::fromDomain)
                .toList();
    }
}
