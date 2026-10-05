package com.example.tarea.application.assessmenttype.usecase;

import com.example.tarea.application.assessmenttype.command.UpdateAssessmentTypeCommand;
import com.example.tarea.application.assessmenttype.dto.AssessmentTypeResponse;
import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.assessmenttype.exception.AssessmentTypeNotFoundException;
import com.example.tarea.domain.assessmenttype.model.aggregate.AssessmentType;
import com.example.tarea.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class UpdateAssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateAssessmentTypeUseCase(AssessmentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public AssessmentTypeResponse execute(UpdateAssessmentTypeCommand command) {

        AssessmentType assessmentType = repository.findById(command.id())
                .orElseThrow(() -> new AssessmentTypeNotFoundException(command.id()));

        assessmentType.update(
                command.code(),
                command.name(),
                command.active(),
                command.description());

        if (repository.existsByCodeAndIdNot(assessmentType.code(), assessmentType.id())) {
            throw new ConflictApplicationException(
                    "A AssessmentType with the same code already exists");
        }

        AssessmentType saved = repository.save(assessmentType);

        eventPublisher.publish(assessmentType.domainEvents());
        assessmentType.clearDomainEvents();

        return AssessmentTypeResponse.fromDomain(saved);
    }
}
