package com.example.tarea.application.emailcontact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.emailcontact.exception.EmailContactNotFoundException;
import com.example.tarea.domain.emailcontact.model.aggregate.EmailContact;
import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;
import com.example.tarea.domain.emailcontact.port.repository.EmailContactRepository;

public class DeleteEmailContactUseCase {

    private final EmailContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteEmailContactUseCase(EmailContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(EmailContactId id) {

        EmailContact emailContact = repository.findById(id)
                .orElseThrow(() -> new EmailContactNotFoundException(id));

        emailContact.delete();
        repository.delete(emailContact);

        eventPublisher.publish(emailContact.domainEvents());
        emailContact.clearDomainEvents();
    }
}
