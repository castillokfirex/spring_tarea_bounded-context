package com.example.tarea.application.patient.usecase;

import java.util.List;

import com.example.tarea.application.patient.dto.PatientResponse;
import com.example.tarea.domain.patient.port.repository.PatientRepository;

public class ListPatientUseCase {

    private final PatientRepository repository;

    public ListPatientUseCase(PatientRepository repository) {
        this.repository = repository;
    }

    public List<PatientResponse> execute() {
        return repository.findAll()
                .stream()
                .map(PatientResponse::fromDomain)
                .toList();
    }
}
