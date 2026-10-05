package com.example.tarea.application.relationshiptype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.relationshiptype.command.RegisterRelationshipTypeCommand;
import com.example.tarea.application.relationshiptype.dto.RelationshipTypeResponse;
import com.example.tarea.domain.relationshiptype.model.aggregate.RelationshipType;
import com.example.tarea.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class RegisterRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterRelationshipTypeUseCase(RelationshipTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public RelationshipTypeResponse execute(RegisterRelationshipTypeCommand command) {

        RelationshipType relationshipType = RelationshipType.register(
                command.description());

        if (repository.existsByDescription(relationshipType.description())) {
            throw new ConflictApplicationException(
                    "A RelationshipType with the same description already exists");
        }

        RelationshipType saved = repository.save(relationshipType);

        eventPublisher.publish(relationshipType.domainEvents());
        relationshipType.clearDomainEvents();

        return RelationshipTypeResponse.fromDomain(saved);
    }
}
