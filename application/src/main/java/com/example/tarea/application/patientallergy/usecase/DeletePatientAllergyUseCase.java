package com.example.tarea.application.patientallergy.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.patientallergy.exception.PatientAllergyNotFoundException;
import com.example.tarea.domain.patientallergy.model.aggregate.PatientAllergy;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.example.tarea.domain.patientallergy.port.repository.PatientAllergyRepository;

public class DeletePatientAllergyUseCase {

    private final PatientAllergyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeletePatientAllergyUseCase(PatientAllergyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(PatientAllergyId id) {

        PatientAllergy patientAllergy = repository.findById(id)
                .orElseThrow(() -> new PatientAllergyNotFoundException(id));

        patientAllergy.delete();
        repository.delete(patientAllergy);

        eventPublisher.publish(patientAllergy.domainEvents());
        patientAllergy.clearDomainEvents();
    }
}
