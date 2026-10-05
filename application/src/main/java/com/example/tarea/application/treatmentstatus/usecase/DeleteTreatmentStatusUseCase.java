package com.example.tarea.application.treatmentstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.treatmentstatus.exception.TreatmentStatusNotFoundException;
import com.example.tarea.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.example.tarea.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class DeleteTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteTreatmentStatusUseCase(TreatmentStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(TreatmentStatusId id) {

        TreatmentStatus treatmentStatus = repository.findById(id)
                .orElseThrow(() -> new TreatmentStatusNotFoundException(id));

        treatmentStatus.delete();
        repository.delete(treatmentStatus);

        eventPublisher.publish(treatmentStatus.domainEvents());
        treatmentStatus.clearDomainEvents();
    }
}
