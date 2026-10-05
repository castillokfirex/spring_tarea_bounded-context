package com.example.tarea.application.patient.usecase;

import com.example.tarea.application.patient.dto.PatientResponse;
import com.example.tarea.domain.patient.exception.PatientNotFoundException;
import com.example.tarea.domain.patient.model.valueobject.PatientId;
import com.example.tarea.domain.patient.port.repository.PatientRepository;

public class GetPatientByIdUseCase {

    private final PatientRepository repository;

    public GetPatientByIdUseCase(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientResponse execute(PatientId id) {
        return repository.findById(id)
                .map(PatientResponse::fromDomain)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }
}
