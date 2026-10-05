package com.example.tarea.application.patientcontact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.patientcontact.command.UpdatePatientContactCommand;
import com.example.tarea.application.patientcontact.dto.PatientContactResponse;
import com.example.tarea.domain.patientcontact.exception.PatientContactNotFoundException;
import com.example.tarea.domain.patientcontact.model.aggregate.PatientContact;
import com.example.tarea.domain.patientcontact.port.repository.PatientContactRepository;

public class UpdatePatientContactUseCase {

    private final PatientContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdatePatientContactUseCase(PatientContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PatientContactResponse execute(UpdatePatientContactCommand command) {

        PatientContact patientContact = repository.findById(command.id())
                .orElseThrow(() -> new PatientContactNotFoundException(command.id()));

        patientContact.update(
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
