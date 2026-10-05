package com.example.tarea.application.documenttype.usecase;

import java.util.List;

import com.example.tarea.application.documenttype.dto.DocumentTypeResponse;
import com.example.tarea.domain.documenttype.port.repository.DocumentTypeRepository;

public class ListDocumentTypeUseCase {

    private final DocumentTypeRepository repository;

    public ListDocumentTypeUseCase(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    public List<DocumentTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(DocumentTypeResponse::fromDomain)
                .toList();
    }
}
