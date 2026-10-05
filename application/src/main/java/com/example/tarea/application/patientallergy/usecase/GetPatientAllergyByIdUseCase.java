package com.example.tarea.application.patientallergy.usecase;

import com.example.tarea.application.patientallergy.dto.PatientAllergyResponse;
import com.example.tarea.domain.patientallergy.exception.PatientAllergyNotFoundException;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.example.tarea.domain.patientallergy.port.repository.PatientAllergyRepository;

public class GetPatientAllergyByIdUseCase {

    private final PatientAllergyRepository repository;

    public GetPatientAllergyByIdUseCase(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public PatientAllergyResponse execute(PatientAllergyId id) {
        return repository.findById(id)
                .map(PatientAllergyResponse::fromDomain)
                .orElseThrow(() -> new PatientAllergyNotFoundException(id));
    }
}
