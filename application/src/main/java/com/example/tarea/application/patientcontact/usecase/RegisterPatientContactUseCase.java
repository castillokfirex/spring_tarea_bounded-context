package com.example.tarea.application.patientcontact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.patientcontact.command.RegisterPatientContactCommand;
import com.example.tarea.application.patientcontact.dto.PatientContactResponse;
import com.example.tarea.domain.patientcontact.model.aggregate.PatientContact;
import com.example.tarea.domain.patientcontact.port.repository.PatientContactRepository;

public class RegisterPatientContactUseCase {

    private final PatientContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterPatientContactUseCase(PatientContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PatientContactResponse execute(RegisterPatientContactCommand command) {

        PatientContact patientContact = PatientContact.register(
                command.contactId(),
                command.patientId(),
                command.isPrimaryContact(),
                command.isEmergencyContact(),
                command.relationshipTypeId());

        PatientContact saved = repository.save(patientContact);

        eventPublisher.publish(patientContact.domainEvents());
        patientContact.clearDomainEvents();

        return PatientContactResponse.fromDomain(saved);
    }
}
