package com.example.tarea.application.documenttype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.documenttype.exception.DocumentTypeNotFoundException;
import com.example.tarea.domain.documenttype.model.aggregate.DocumentType;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;
import com.example.tarea.domain.documenttype.port.repository.DocumentTypeRepository;

public class DeleteDocumentTypeUseCase {

    private final DocumentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteDocumentTypeUseCase(DocumentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(DocumentTypeId id) {

        DocumentType documentType = repository.findById(id)
                .orElseThrow(() -> new DocumentTypeNotFoundException(id));

        documentType.delete();
        repository.delete(documentType);

        eventPublisher.publish(documentType.domainEvents());
        documentType.clearDomainEvents();
    }
}
