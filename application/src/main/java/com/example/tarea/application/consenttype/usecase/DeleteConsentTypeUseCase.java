package com.example.tarea.application.consenttype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.consenttype.exception.ConsentTypeNotFoundException;
import com.example.tarea.domain.consenttype.model.aggregate.ConsentType;
import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;
import com.example.tarea.domain.consenttype.port.repository.ConsentTypeRepository;

public class DeleteConsentTypeUseCase {

    private final ConsentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteConsentTypeUseCase(ConsentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ConsentTypeId id) {

        ConsentType consentType = repository.findById(id)
                .orElseThrow(() -> new ConsentTypeNotFoundException(id));

        consentType.delete();
        repository.delete(consentType);

        eventPublisher.publish(consentType.domainEvents());
        consentType.clearDomainEvents();
    }
}
