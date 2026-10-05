package com.example.tarea.application.consenttype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.consenttype.command.RegisterConsentTypeCommand;
import com.example.tarea.application.consenttype.dto.ConsentTypeResponse;
import com.example.tarea.domain.consenttype.model.aggregate.ConsentType;
import com.example.tarea.domain.consenttype.port.repository.ConsentTypeRepository;

public class RegisterConsentTypeUseCase {

    private final ConsentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterConsentTypeUseCase(ConsentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ConsentTypeResponse execute(RegisterConsentTypeCommand command) {

        ConsentType consentType = ConsentType.register(
                command.code(),
                command.name(),
                command.active(),
                command.description());

        if (repository.existsByCode(consentType.code())) {
            throw new ConflictApplicationException(
                    "A ConsentType with the same code already exists");
        }

        ConsentType saved = repository.save(consentType);

        eventPublisher.publish(consentType.domainEvents());
        consentType.clearDomainEvents();

        return ConsentTypeResponse.fromDomain(saved);
    }
}
