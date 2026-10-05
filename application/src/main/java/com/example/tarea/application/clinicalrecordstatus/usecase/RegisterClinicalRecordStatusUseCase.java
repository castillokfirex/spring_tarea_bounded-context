package com.example.tarea.application.clinicalrecordstatus.usecase;

import com.example.tarea.application.clinicalrecordstatus.command.RegisterClinicalRecordStatusCommand;
import com.example.tarea.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.example.tarea.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class RegisterClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ClinicalRecordStatusResponse execute(RegisterClinicalRecordStatusCommand command) {

        ClinicalRecordStatus clinicalRecordStatus = ClinicalRecordStatus.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(clinicalRecordStatus.code())) {
            throw new ConflictApplicationException(
                    "A ClinicalRecordStatus with the same code already exists");
        }
        if (repository.existsByName(clinicalRecordStatus.name())) {
            throw new ConflictApplicationException(
                    "A ClinicalRecordStatus with the same name already exists");
        }

        ClinicalRecordStatus saved = repository.save(clinicalRecordStatus);

        eventPublisher.publish(clinicalRecordStatus.domainEvents());
        clinicalRecordStatus.clearDomainEvents();

        return ClinicalRecordStatusResponse.fromDomain(saved);
    }
}
