package com.example.tarea.application.consenttype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.consenttype.command.UpdateConsentTypeCommand;
import com.example.tarea.application.consenttype.dto.ConsentTypeResponse;
import com.example.tarea.domain.consenttype.exception.ConsentTypeNotFoundException;
import com.example.tarea.domain.consenttype.model.aggregate.ConsentType;
import com.example.tarea.domain.consenttype.port.repository.ConsentTypeRepository;

public class UpdateConsentTypeUseCase {

    private final ConsentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateConsentTypeUseCase(ConsentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ConsentTypeResponse execute(UpdateConsentTypeCommand command) {

        ConsentType consentType = repository.findById(command.id())
                .orElseThrow(() -> new ConsentTypeNotFoundException(command.id()));

        consentType.update(
                command.code(),
                command.name(),
                command.active(),
                command.description());

        if (repository.existsByCodeAndIdNot(consentType.code(), consentType.id())) {
            throw new ConflictApplicationException(
                    "A ConsentType with the same code already exists");
        }

        ConsentType saved = repository.save(consentType);

        eventPublisher.publish(consentType.domainEvents());
        consentType.clearDomainEvents();

        return ConsentTypeResponse.fromDomain(saved);
    }
}
