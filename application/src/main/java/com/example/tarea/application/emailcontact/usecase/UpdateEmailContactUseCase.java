package com.example.tarea.application.emailcontact.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.emailcontact.command.UpdateEmailContactCommand;
import com.example.tarea.application.emailcontact.dto.EmailContactResponse;
import com.example.tarea.domain.emailcontact.exception.EmailContactNotFoundException;
import com.example.tarea.domain.emailcontact.model.aggregate.EmailContact;
import com.example.tarea.domain.emailcontact.port.repository.EmailContactRepository;

public class UpdateEmailContactUseCase {

    private final EmailContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateEmailContactUseCase(EmailContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EmailContactResponse execute(UpdateEmailContactCommand command) {

        EmailContact emailContact = repository.findById(command.id())
                .orElseThrow(() -> new EmailContactNotFoundException(command.id()));

        emailContact.update(
                command.contactId(),
                command.email(),
                command.notes());

        if (repository.existsByEmailAndIdNot(emailContact.email(), emailContact.id())) {
            throw new ConflictApplicationException(
                    "A EmailContact with the same email already exists");
        }

        EmailContact saved = repository.save(emailContact);

        eventPublisher.publish(emailContact.domainEvents());
        emailContact.clearDomainEvents();

        return EmailContactResponse.fromDomain(saved);
    }
}
