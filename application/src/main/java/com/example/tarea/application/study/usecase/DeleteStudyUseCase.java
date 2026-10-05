package com.example.tarea.application.study.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.study.exception.StudyNotFoundException;
import com.example.tarea.domain.study.model.aggregate.Study;
import com.example.tarea.domain.study.model.valueobject.StudyId;
import com.example.tarea.domain.study.port.repository.StudyRepository;

public class DeleteStudyUseCase {

    private final StudyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteStudyUseCase(StudyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(StudyId id) {

        Study study = repository.findById(id)
                .orElseThrow(() -> new StudyNotFoundException(id));

        study.delete();
        repository.delete(study);

        eventPublisher.publish(study.domainEvents());
        study.clearDomainEvents();
    }
}
