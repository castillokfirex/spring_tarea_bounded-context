package com.example.tarea.application.assessmenttype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.assessmenttype.exception.AssessmentTypeNotFoundException;
import com.example.tarea.domain.assessmenttype.model.aggregate.AssessmentType;
import com.example.tarea.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.example.tarea.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class DeleteAssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteAssessmentTypeUseCase(AssessmentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(AssessmentTypeId id) {

        AssessmentType assessmentType = repository.findById(id)
                .orElseThrow(() -> new AssessmentTypeNotFoundException(id));

        assessmentType.delete();
        repository.delete(assessmentType);

        eventPublisher.publish(assessmentType.domainEvents());
        assessmentType.clearDomainEvents();
    }
}
