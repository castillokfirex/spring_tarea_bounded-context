package com.example.tarea.application.gender.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.gender.command.RegisterGenderCommand;
import com.example.tarea.application.gender.dto.GenderResponse;
import com.example.tarea.domain.gender.model.aggregate.Gender;
import com.example.tarea.domain.gender.port.repository.GenderRepository;

public class RegisterGenderUseCase {

    private final GenderRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterGenderUseCase(GenderRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public GenderResponse execute(RegisterGenderCommand command) {

        Gender gender = Gender.register(
                command.description());

        if (repository.existsByDescription(gender.description())) {
            throw new ConflictApplicationException(
                    "A Gender with the same description already exists");
        }

        Gender saved = repository.save(gender);

        eventPublisher.publish(gender.domainEvents());
        gender.clearDomainEvents();

        return GenderResponse.fromDomain(saved);
    }
}
