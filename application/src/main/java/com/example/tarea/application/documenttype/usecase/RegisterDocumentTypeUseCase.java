package com.example.tarea.application.documenttype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.documenttype.command.RegisterDocumentTypeCommand;
import com.example.tarea.application.documenttype.dto.DocumentTypeResponse;
import com.example.tarea.domain.documenttype.model.aggregate.DocumentType;
import com.example.tarea.domain.documenttype.port.repository.DocumentTypeRepository;

public class RegisterDocumentTypeUseCase {

    private final DocumentTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterDocumentTypeUseCase(DocumentTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public DocumentTypeResponse execute(RegisterDocumentTypeCommand command) {

        DocumentType documentType = DocumentType.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(documentType.code())) {
            throw new ConflictApplicationException(
                    "A DocumentType with the same code already exists");
        }

        DocumentType saved = repository.save(documentType);

        eventPublisher.publish(documentType.domainEvents());
        documentType.clearDomainEvents();

        return DocumentTypeResponse.fromDomain(saved);
    }
}
