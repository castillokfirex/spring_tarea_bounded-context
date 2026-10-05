package com.example.tarea.application.treatmentstatus.usecase;

import java.util.List;

import com.example.tarea.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.example.tarea.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class ListTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;

    public ListTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    public List<TreatmentStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(TreatmentStatusResponse::fromDomain)
                .toList();
    }
}
