package com.example.tarea.application.contact.usecase;

import com.example.tarea.application.contact.dto.ContactResponse;
import com.example.tarea.domain.contact.exception.ContactNotFoundException;
import com.example.tarea.domain.contact.model.valueobject.ContactId;
import com.example.tarea.domain.contact.port.repository.ContactRepository;

public class GetContactByIdUseCase {

    private final ContactRepository repository;

    public GetContactByIdUseCase(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactResponse execute(ContactId id) {
        return repository.findById(id)
                .map(ContactResponse::fromDomain)
                .orElseThrow(() -> new ContactNotFoundException(id));
    }
}
