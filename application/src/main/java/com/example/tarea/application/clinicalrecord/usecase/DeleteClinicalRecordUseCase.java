package com.example.tarea.application.clinicalrecord.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecord.exception.ClinicalRecordNotFoundException;
import com.example.tarea.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.example.tarea.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class DeleteClinicalRecordUseCase {

    private final ClinicalRecordRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteClinicalRecordUseCase(ClinicalRecordRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ClinicalRecordId id) {

        ClinicalRecord clinicalRecord = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordNotFoundException(id));

        clinicalRecord.delete();
        repository.delete(clinicalRecord);

        eventPublisher.publish(clinicalRecord.domainEvents());
        clinicalRecord.clearDomainEvents();
    }
}
