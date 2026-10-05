package com.example.tarea.application.relationshiptype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.relationshiptype.command.UpdateRelationshipTypeCommand;
import com.example.tarea.application.relationshiptype.dto.RelationshipTypeResponse;
import com.example.tarea.domain.relationshiptype.exception.RelationshipTypeNotFoundException;
import com.example.tarea.domain.relationshiptype.model.aggregate.RelationshipType;
import com.example.tarea.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class UpdateRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateRelationshipTypeUseCase(RelationshipTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public RelationshipTypeResponse execute(UpdateRelationshipTypeCommand command) {

        RelationshipType relationshipType = repository.findById(command.id())
                .orElseThrow(() -> new RelationshipTypeNotFoundException(command.id()));

        relationshipType.update(
                command.description());

        if (repository.existsByDescriptionAndIdNot(relationshipType.description(), relationshipType.id())) {
            throw new ConflictApplicationException(
                    "A RelationshipType with the same description already exists");
        }

        RelationshipType saved = repository.save(relationshipType);

        eventPublisher.publish(relationshipType.domainEvents());
        relationshipType.clearDomainEvents();

        return RelationshipTypeResponse.fromDomain(saved);
    }
}
