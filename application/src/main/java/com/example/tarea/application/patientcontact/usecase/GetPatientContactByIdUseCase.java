package com.example.tarea.application.patientcontact.usecase;

import com.example.tarea.application.patientcontact.dto.PatientContactResponse;
import com.example.tarea.domain.patientcontact.exception.PatientContactNotFoundException;
import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;
import com.example.tarea.domain.patientcontact.port.repository.PatientContactRepository;

public class GetPatientContactByIdUseCase {

    private final PatientContactRepository repository;

    public GetPatientContactByIdUseCase(PatientContactRepository repository) {
        this.repository = repository;
    }

    public PatientContactResponse execute(PatientContactId id) {
        return repository.findById(id)
                .map(PatientContactResponse::fromDomain)
                .orElseThrow(() -> new PatientContactNotFoundException(id));
    }
}
