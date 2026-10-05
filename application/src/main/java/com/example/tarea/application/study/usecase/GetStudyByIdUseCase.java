package com.example.tarea.application.study.usecase;

import com.example.tarea.application.study.dto.StudyResponse;
import com.example.tarea.domain.study.exception.StudyNotFoundException;
import com.example.tarea.domain.study.model.valueobject.StudyId;
import com.example.tarea.domain.study.port.repository.StudyRepository;

public class GetStudyByIdUseCase {

    private final StudyRepository repository;

    public GetStudyByIdUseCase(StudyRepository repository) {
        this.repository = repository;
    }

    public StudyResponse execute(StudyId id) {
        return repository.findById(id)
                .map(StudyResponse::fromDomain)
                .orElseThrow(() -> new StudyNotFoundException(id));
    }
}
