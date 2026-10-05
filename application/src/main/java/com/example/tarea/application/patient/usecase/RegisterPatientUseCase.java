package com.example.tarea.application.patient.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.patient.command.RegisterPatientCommand;
import com.example.tarea.application.patient.dto.PatientResponse;
import com.example.tarea.domain.patient.model.aggregate.Patient;
import com.example.tarea.domain.patient.port.repository.PatientRepository;

public class RegisterPatientUseCase {

    private final PatientRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterPatientUseCase(PatientRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PatientResponse execute(RegisterPatientCommand command) {

        Patient patient = Patient.register(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.secondLastName(),
                command.birthDate(),
                command.biologicalSexId(),
                command.genderIdentity(),
                command.email(),
                command.phone(),
                command.address(),
                command.active(),
                command.createdBy(),
                command.updatedBy(),
                command.cityId());

        if (repository.existsByEmail(patient.email())) {
            throw new ConflictApplicationException(
                    "A Patient with the same email already exists");
        }
        if (repository.existsByDocumentTypeIdAndDocumentNumber(patient.documentTypeId(), patient.documentNumber())) {
            throw new ConflictApplicationException(
                    "A Patient with the same documentTypeId and documentNumber already exists");
        }

        Patient saved = repository.save(patient);

        eventPublisher.publish(patient.domainEvents());
        patient.clearDomainEvents();

        return PatientResponse.fromDomain(saved);
    }
}
