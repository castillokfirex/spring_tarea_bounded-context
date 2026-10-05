package com.example.tarea.application.gender.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.gender.exception.GenderNotFoundException;
import com.example.tarea.domain.gender.model.aggregate.Gender;
import com.example.tarea.domain.gender.model.valueobject.GenderId;
import com.example.tarea.domain.gender.port.repository.GenderRepository;

public class DeleteGenderUseCase {

    private final GenderRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteGenderUseCase(GenderRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(GenderId id) {

        Gender gender = repository.findById(id)
                .orElseThrow(() -> new GenderNotFoundException(id));

        gender.delete();
        repository.delete(gender);

        eventPublisher.publish(gender.domainEvents());
        gender.clearDomainEvents();
    }
}
