package com.example.tarea.application.clinicalrecord.usecase;

import com.example.tarea.application.clinicalrecord.command.RegisterClinicalRecordCommand;
import com.example.tarea.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.example.tarea.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class RegisterClinicalRecordUseCase {

    private final ClinicalRecordRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterClinicalRecordUseCase(ClinicalRecordRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ClinicalRecordResponse execute(RegisterClinicalRecordCommand command) {

        ClinicalRecord clinicalRecord = ClinicalRecord.register(
                command.patientId(),
                command.creationDate(),
                command.recordNumber(),
                command.openedAt(),
                command.closedAt(),
                command.statusId(),
                command.createdBy());

        if (repository.existsByRecordNumber(clinicalRecord.recordNumber())) {
            throw new ConflictApplicationException(
                    "A ClinicalRecord with the same recordNumber already exists");
        }

        ClinicalRecord saved = repository.save(clinicalRecord);

        eventPublisher.publish(clinicalRecord.domainEvents());
        clinicalRecord.clearDomainEvents();

        return ClinicalRecordResponse.fromDomain(saved);
    }
}
