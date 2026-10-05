package com.example.tarea.application.study.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.study.command.UpdateStudyCommand;
import com.example.tarea.application.study.dto.StudyResponse;
import com.example.tarea.domain.study.exception.StudyNotFoundException;
import com.example.tarea.domain.study.model.aggregate.Study;
import com.example.tarea.domain.study.port.repository.StudyRepository;

public class UpdateStudyUseCase {

    private final StudyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateStudyUseCase(StudyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public StudyResponse execute(UpdateStudyCommand command) {

        Study study = repository.findById(command.id())
                .orElseThrow(() -> new StudyNotFoundException(command.id()));

        study.update(
                command.name());

        Study saved = repository.save(study);

        eventPublisher.publish(study.domainEvents());
        study.clearDomainEvents();

        return StudyResponse.fromDomain(saved);
    }
}
