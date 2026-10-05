package com.example.tarea.application.emailcontact.usecase;

import com.example.tarea.application.emailcontact.dto.EmailContactResponse;
import com.example.tarea.domain.emailcontact.exception.EmailContactNotFoundException;
import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;
import com.example.tarea.domain.emailcontact.port.repository.EmailContactRepository;

public class GetEmailContactByIdUseCase {

    private final EmailContactRepository repository;

    public GetEmailContactByIdUseCase(EmailContactRepository repository) {
        this.repository = repository;
    }

    public EmailContactResponse execute(EmailContactId id) {
        return repository.findById(id)
                .map(EmailContactResponse::fromDomain)
                .orElseThrow(() -> new EmailContactNotFoundException(id));
    }
}
