package com.example.tarea.application.patientallergy.usecase;

import java.util.List;

import com.example.tarea.application.patientallergy.dto.PatientAllergyResponse;
import com.example.tarea.domain.patientallergy.port.repository.PatientAllergyRepository;

public class ListPatientAllergyUseCase {

    private final PatientAllergyRepository repository;

    public ListPatientAllergyUseCase(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public List<PatientAllergyResponse> execute() {
        return repository.findAll()
                .stream()
                .map(PatientAllergyResponse::fromDomain)
                .toList();
    }
}
