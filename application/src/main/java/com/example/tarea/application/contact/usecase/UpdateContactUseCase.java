package com.example.tarea.application.contact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.contact.command.UpdateContactCommand;
import com.example.tarea.application.contact.dto.ContactResponse;
import com.example.tarea.domain.contact.exception.ContactNotFoundException;
import com.example.tarea.domain.contact.model.aggregate.Contact;
import com.example.tarea.domain.contact.port.repository.ContactRepository;

public class UpdateContactUseCase {

    private final ContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateContactUseCase(ContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ContactResponse execute(UpdateContactCommand command) {

        Contact contact = repository.findById(command.id())
                .orElseThrow(() -> new ContactNotFoundException(command.id()));

        contact.update(
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId(),
                command.updatedBy());

        Contact saved = repository.save(contact);

        eventPublisher.publish(contact.domainEvents());
        contact.clearDomainEvents();

        return ContactResponse.fromDomain(saved);
    }
}
