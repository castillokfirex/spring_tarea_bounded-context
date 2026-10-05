package com.example.tarea.application.mentalstatusexam.usecase;

import java.util.List;

import com.example.tarea.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.example.tarea.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class ListMentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;

    public ListMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        this.repository = repository;
    }

    public List<MentalStatusExamResponse> execute() {
        return repository.findAll()
                .stream()
                .map(MentalStatusExamResponse::fromDomain)
                .toList();
    }
}
