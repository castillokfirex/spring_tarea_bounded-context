package com.example.tarea.application.mentalstatusexam.usecase;

import com.example.tarea.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.example.tarea.domain.mentalstatusexam.exception.MentalStatusExamNotFoundException;
import com.example.tarea.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.example.tarea.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class GetMentalStatusExamByIdUseCase {

    private final MentalStatusExamRepository repository;

    public GetMentalStatusExamByIdUseCase(MentalStatusExamRepository repository) {
        this.repository = repository;
    }

    public MentalStatusExamResponse execute(MentalStatusExamId id) {
        return repository.findById(id)
                .map(MentalStatusExamResponse::fromDomain)
                .orElseThrow(() -> new MentalStatusExamNotFoundException(id));
    }
}
