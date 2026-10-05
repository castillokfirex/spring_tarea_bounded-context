package com.example.tarea.application.patientcontact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.patientcontact.exception.PatientContactNotFoundException;
import com.example.tarea.domain.patientcontact.model.aggregate.PatientContact;
import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;
import com.example.tarea.domain.patientcontact.port.repository.PatientContactRepository;

public class DeletePatientContactUseCase {

    private final PatientContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeletePatientContactUseCase(PatientContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(PatientContactId id) {

        PatientContact patientContact = repository.findById(id)
                .orElseThrow(() -> new PatientContactNotFoundException(id));

        patientContact.delete();
        repository.delete(patientContact);

        eventPublisher.publish(patientContact.domainEvents());
        patientContact.clearDomainEvents();
    }
}
