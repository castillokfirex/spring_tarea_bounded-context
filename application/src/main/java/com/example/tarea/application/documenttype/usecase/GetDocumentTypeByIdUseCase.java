package com.example.tarea.application.documenttype.usecase;

import com.example.tarea.application.documenttype.dto.DocumentTypeResponse;
import com.example.tarea.domain.documenttype.exception.DocumentTypeNotFoundException;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;
import com.example.tarea.domain.documenttype.port.repository.DocumentTypeRepository;

public class GetDocumentTypeByIdUseCase {

    private final DocumentTypeRepository repository;

    public GetDocumentTypeByIdUseCase(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    public DocumentTypeResponse execute(DocumentTypeId id) {
        return repository.findById(id)
                .map(DocumentTypeResponse::fromDomain)
                .orElseThrow(() -> new DocumentTypeNotFoundException(id));
    }
}
