package com.example.tarea.application.clinicalrecordstatus.usecase;

import com.example.tarea.application.clinicalrecordstatus.command.UpdateClinicalRecordStatusCommand;
import com.example.tarea.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundException;
import com.example.tarea.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.example.tarea.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class UpdateClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ClinicalRecordStatusResponse execute(UpdateClinicalRecordStatusCommand command) {

        ClinicalRecordStatus clinicalRecordStatus = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundException(command.id()));

        clinicalRecordStatus.update(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCodeAndIdNot(clinicalRecordStatus.code(), clinicalRecordStatus.id())) {
            throw new ConflictApplicationException(
                    "A ClinicalRecordStatus with the same code already exists");
        }
        if (repository.existsByNameAndIdNot(clinicalRecordStatus.name(), clinicalRecordStatus.id())) {
            throw new ConflictApplicationException(
                    "A ClinicalRecordStatus with the same name already exists");
        }

        ClinicalRecordStatus saved = repository.save(clinicalRecordStatus);

        eventPublisher.publish(clinicalRecordStatus.domainEvents());
        clinicalRecordStatus.clearDomainEvents();

        return ClinicalRecordStatusResponse.fromDomain(saved);
    }
}
