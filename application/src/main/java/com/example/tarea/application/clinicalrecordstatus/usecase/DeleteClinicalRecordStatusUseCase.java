package com.example.tarea.application.clinicalrecordstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundException;
import com.example.tarea.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.example.tarea.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class DeleteClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ClinicalRecordStatusId id) {

        ClinicalRecordStatus clinicalRecordStatus = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundException(id));

        clinicalRecordStatus.delete();
        repository.delete(clinicalRecordStatus);

        eventPublisher.publish(clinicalRecordStatus.domainEvents());
        clinicalRecordStatus.clearDomainEvents();
    }
}
