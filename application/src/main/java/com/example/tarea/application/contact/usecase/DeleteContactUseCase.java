package com.example.tarea.application.contact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.contact.exception.ContactNotFoundException;
import com.example.tarea.domain.contact.model.aggregate.Contact;
import com.example.tarea.domain.contact.model.valueobject.ContactId;
import com.example.tarea.domain.contact.port.repository.ContactRepository;

public class DeleteContactUseCase {

    private final ContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteContactUseCase(ContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ContactId id) {

        Contact contact = repository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));

        contact.delete();
        repository.delete(contact);

        eventPublisher.publish(contact.domainEvents());
        contact.clearDomainEvents();
    }
}
