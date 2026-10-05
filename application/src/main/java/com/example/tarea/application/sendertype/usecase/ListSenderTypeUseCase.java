package com.example.tarea.application.sendertype.usecase;

import java.util.List;

import com.example.tarea.application.sendertype.dto.SenderTypeResponse;
import com.example.tarea.domain.sendertype.port.repository.SenderTypeRepository;

public class ListSenderTypeUseCase {

    private final SenderTypeRepository repository;

    public ListSenderTypeUseCase(SenderTypeRepository repository) {
        this.repository = repository;
    }

    public List<SenderTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(SenderTypeResponse::fromDomain)
                .toList();
    }
}
