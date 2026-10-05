package com.example.tarea.application.patient.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.patient.exception.PatientNotFoundException;
import com.example.tarea.domain.patient.model.aggregate.Patient;
import com.example.tarea.domain.patient.model.valueobject.PatientId;
import com.example.tarea.domain.patient.port.repository.PatientRepository;

public class DeletePatientUseCase {

    private final PatientRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeletePatientUseCase(PatientRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(PatientId id) {

        Patient patient = repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        patient.delete();
        repository.delete(patient);

        eventPublisher.publish(patient.domainEvents());
        patient.clearDomainEvents();
    }
}
