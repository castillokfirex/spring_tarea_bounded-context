package com.example.tarea.application.phonecontact.usecase;

import java.util.List;

import com.example.tarea.application.phonecontact.dto.PhoneContactResponse;
import com.example.tarea.domain.phonecontact.port.repository.PhoneContactRepository;

public class ListPhoneContactUseCase {

    private final PhoneContactRepository repository;

    public ListPhoneContactUseCase(PhoneContactRepository repository) {
        this.repository = repository;
    }

    public List<PhoneContactResponse> execute() {
        return repository.findAll()
                .stream()
                .map(PhoneContactResponse::fromDomain)
                .toList();
    }
}
