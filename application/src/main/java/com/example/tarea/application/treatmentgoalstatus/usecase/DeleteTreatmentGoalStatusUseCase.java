package com.example.tarea.application.treatmentgoalstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundException;
import com.example.tarea.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.example.tarea.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class DeleteTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(TreatmentGoalStatusId id) {

        TreatmentGoalStatus treatmentGoalStatus = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundException(id));

        treatmentGoalStatus.delete();
        repository.delete(treatmentGoalStatus);

        eventPublisher.publish(treatmentGoalStatus.domainEvents());
        treatmentGoalStatus.clearDomainEvents();
    }
}
