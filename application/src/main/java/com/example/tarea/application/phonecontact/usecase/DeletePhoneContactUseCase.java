package com.example.tarea.application.phonecontact.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.phonecontact.exception.PhoneContactNotFoundException;
import com.example.tarea.domain.phonecontact.model.aggregate.PhoneContact;
import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;
import com.example.tarea.domain.phonecontact.port.repository.PhoneContactRepository;

public class DeletePhoneContactUseCase {

    private final PhoneContactRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeletePhoneContactUseCase(PhoneContactRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(PhoneContactId id) {

        PhoneContact phoneContact = repository.findById(id)
                .orElseThrow(() -> new PhoneContactNotFoundException(id));

        phoneContact.delete();
        repository.delete(phoneContact);

        eventPublisher.publish(phoneContact.domainEvents());
        phoneContact.clearDomainEvents();
    }
}
