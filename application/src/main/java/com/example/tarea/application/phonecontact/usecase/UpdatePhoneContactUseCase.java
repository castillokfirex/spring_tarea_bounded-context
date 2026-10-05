package com.example.tarea.application.phonecontact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.phonecontact.command.UpdatePhoneContactCommand;
import com.example.tarea.application.phonecontact.dto.PhoneContactResponse;
import com.example.tarea.domain.phonecontact.exception.PhoneContactNotFoundException;
import com.example.tarea.domain.phonecontact.model.aggregate.PhoneContact;
import com.example.tarea.domain.phonecontact.port.repository.PhoneContactRepository;

public class UpdatePhoneContactUseCase {

    private final PhoneContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdatePhoneContactUseCase(PhoneContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PhoneContactResponse execute(UpdatePhoneContactCommand command) {

        PhoneContact phoneContact = repository.findById(command.id())
                .orElseThrow(() -> new PhoneContactNotFoundException(command.id()));

        phoneContact.update(
                command.contactId(),
                command.phone(),
                command.notes());

        PhoneContact saved = repository.save(phoneContact);

        eventPublisher.publish(phoneContact.domainEvents());
        phoneContact.clearDomainEvents();

        return PhoneContactResponse.fromDomain(saved);
    }
}
