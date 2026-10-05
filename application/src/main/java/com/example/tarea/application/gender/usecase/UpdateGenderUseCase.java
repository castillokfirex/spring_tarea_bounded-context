package com.example.tarea.application.gender.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.gender.command.UpdateGenderCommand;
import com.example.tarea.application.gender.dto.GenderResponse;
import com.example.tarea.domain.gender.exception.GenderNotFoundException;
import com.example.tarea.domain.gender.model.aggregate.Gender;
import com.example.tarea.domain.gender.port.repository.GenderRepository;

public class UpdateGenderUseCase {

    private final GenderRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateGenderUseCase(GenderRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public GenderResponse execute(UpdateGenderCommand command) {

        Gender gender = repository.findById(command.id())
                .orElseThrow(() -> new GenderNotFoundException(command.id()));

        gender.update(
                command.description());

        if (repository.existsByDescriptionAndIdNot(gender.description(), gender.id())) {
            throw new ConflictApplicationException(
                    "A Gender with the same description already exists");
        }

        Gender saved = repository.save(gender);

        eventPublisher.publish(gender.domainEvents());
        gender.clearDomainEvents();

        return GenderResponse.fromDomain(saved);
    }
}
