package com.example.tarea.application.contact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.contact.command.RegisterContactCommand;
import com.example.tarea.application.contact.dto.ContactResponse;
import com.example.tarea.domain.contact.model.aggregate.Contact;
import com.example.tarea.domain.contact.port.repository.ContactRepository;

public class RegisterContactUseCase {

    private final ContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterContactUseCase(ContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ContactResponse execute(RegisterContactCommand command) {

        Contact contact = Contact.register(
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId(),
                command.createdBy(),
                command.updatedBy());

        Contact saved = repository.save(contact);

        eventPublisher.publish(contact.domainEvents());
        contact.clearDomainEvents();

        return ContactResponse.fromDomain(saved);
    }
}
