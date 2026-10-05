package com.example.tarea.application.study.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.study.command.RegisterStudyCommand;
import com.example.tarea.application.study.dto.StudyResponse;
import com.example.tarea.domain.study.model.aggregate.Study;
import com.example.tarea.domain.study.port.repository.StudyRepository;

public class RegisterStudyUseCase {

    private final StudyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterStudyUseCase(StudyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public StudyResponse execute(RegisterStudyCommand command) {

        Study study = Study.register(
                command.name());

        Study saved = repository.save(study);

        eventPublisher.publish(study.domainEvents());
        study.clearDomainEvents();

        return StudyResponse.fromDomain(saved);
    }
}
