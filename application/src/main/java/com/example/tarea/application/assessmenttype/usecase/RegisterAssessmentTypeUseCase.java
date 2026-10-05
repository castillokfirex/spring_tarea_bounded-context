package com.example.tarea.application.assessmenttype.usecase;

import com.example.tarea.application.assessmenttype.command.RegisterAssessmentTypeCommand;
import com.example.tarea.application.assessmenttype.dto.AssessmentTypeResponse;
import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.assessmenttype.model.aggregate.AssessmentType;
import com.example.tarea.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class RegisterAssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterAssessmentTypeUseCase(AssessmentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public AssessmentTypeResponse execute(RegisterAssessmentTypeCommand command) {

        AssessmentType assessmentType = AssessmentType.register(
                command.code(),
                command.name(),
                command.active(),
                command.description());

        if (repository.existsByCode(assessmentType.code())) {
            throw new ConflictApplicationException(
                    "A AssessmentType with the same code already exists");
        }

        AssessmentType saved = repository.save(assessmentType);

        eventPublisher.publish(assessmentType.domainEvents());
        assessmentType.clearDomainEvents();

        return AssessmentTypeResponse.fromDomain(saved);
    }
}
