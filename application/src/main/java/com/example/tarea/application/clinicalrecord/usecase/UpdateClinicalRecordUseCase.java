package com.example.tarea.application.clinicalrecord.usecase;

import com.example.tarea.application.clinicalrecord.command.UpdateClinicalRecordCommand;
import com.example.tarea.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecord.exception.ClinicalRecordNotFoundException;
import com.example.tarea.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.example.tarea.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class UpdateClinicalRecordUseCase {

    private final ClinicalRecordRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateClinicalRecordUseCase(ClinicalRecordRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ClinicalRecordResponse execute(UpdateClinicalRecordCommand command) {

        ClinicalRecord clinicalRecord = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalRecordNotFoundException(command.id()));

        clinicalRecord.update(
                command.patientId(),
                command.creationDate(),
                command.recordNumber(),
                command.openedAt(),
                command.closedAt(),
                command.statusId());

        if (repository.existsByRecordNumberAndIdNot(clinicalRecord.recordNumber(), clinicalRecord.id())) {
            throw new ConflictApplicationException(
                    "A ClinicalRecord with the same recordNumber already exists");
        }

        ClinicalRecord saved = repository.save(clinicalRecord);

        eventPublisher.publish(clinicalRecord.domainEvents());
        clinicalRecord.clearDomainEvents();

        return ClinicalRecordResponse.fromDomain(saved);
    }
}
