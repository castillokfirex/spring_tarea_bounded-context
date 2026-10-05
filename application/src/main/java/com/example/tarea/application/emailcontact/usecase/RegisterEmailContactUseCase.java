package com.example.tarea.application.emailcontact.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.emailcontact.command.RegisterEmailContactCommand;
import com.example.tarea.application.emailcontact.dto.EmailContactResponse;
import com.example.tarea.domain.emailcontact.model.aggregate.EmailContact;
import com.example.tarea.domain.emailcontact.port.repository.EmailContactRepository;

public class RegisterEmailContactUseCase {

    private final EmailContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterEmailContactUseCase(EmailContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EmailContactResponse execute(RegisterEmailContactCommand command) {

        EmailContact emailContact = EmailContact.register(
                command.contactId(),
                command.email(),
                command.notes());

        if (repository.existsByEmail(emailContact.email())) {
            throw new ConflictApplicationException(
                    "A EmailContact with the same email already exists");
        }

        EmailContact saved = repository.save(emailContact);

        eventPublisher.publish(emailContact.domainEvents());
        emailContact.clearDomainEvents();

        return EmailContactResponse.fromDomain(saved);
    }
}
