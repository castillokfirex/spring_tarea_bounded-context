package com.example.tarea.application.patientcontact.usecase;

import java.util.List;

import com.example.tarea.application.patientcontact.dto.PatientContactResponse;
import com.example.tarea.domain.patientcontact.port.repository.PatientContactRepository;

public class ListPatientContactUseCase {

    private final PatientContactRepository repository;

    public ListPatientContactUseCase(PatientContactRepository repository) {
        this.repository = repository;
    }

    public List<PatientContactResponse> execute() {
        return repository.findAll()
                .stream()
                .map(PatientContactResponse::fromDomain)
                .toList();
    }
}
