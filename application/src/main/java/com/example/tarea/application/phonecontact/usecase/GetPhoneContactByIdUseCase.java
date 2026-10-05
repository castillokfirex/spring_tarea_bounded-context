package com.example.tarea.application.phonecontact.usecase;

import com.example.tarea.application.phonecontact.dto.PhoneContactResponse;
import com.example.tarea.domain.phonecontact.exception.PhoneContactNotFoundException;
import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;
import com.example.tarea.domain.phonecontact.port.repository.PhoneContactRepository;

public class GetPhoneContactByIdUseCase {

    private final PhoneContactRepository repository;

    public GetPhoneContactByIdUseCase(PhoneContactRepository repository) {
        this.repository = repository;
    }

    public PhoneContactResponse execute(PhoneContactId id) {
        return repository.findById(id)
                .map(PhoneContactResponse::fromDomain)
                .orElseThrow(() -> new PhoneContactNotFoundException(id));
    }
}
