package com.example.tarea.application.phonecontact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.phonecontact.command.RegisterPhoneContactCommand;
import com.example.tarea.application.phonecontact.dto.PhoneContactResponse;
import com.example.tarea.domain.phonecontact.model.aggregate.PhoneContact;
import com.example.tarea.domain.phonecontact.port.repository.PhoneContactRepository;

public class RegisterPhoneContactUseCase {

    private final PhoneContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterPhoneContactUseCase(PhoneContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PhoneContactResponse execute(RegisterPhoneContactCommand command) {

        PhoneContact phoneContact = PhoneContact.register(
                command.contactId(),
                command.phone(),
                command.notes());

        PhoneContact saved = repository.save(phoneContact);

        eventPublisher.publish(phoneContact.domainEvents());
        phoneContact.clearDomainEvents();

        return PhoneContactResponse.fromDomain(saved);
    }
}
