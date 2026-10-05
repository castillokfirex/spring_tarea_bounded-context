package com.example.tarea.application.relationshiptype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.relationshiptype.exception.RelationshipTypeNotFoundException;
import com.example.tarea.domain.relationshiptype.model.aggregate.RelationshipType;
import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.example.tarea.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class DeleteRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteRelationshipTypeUseCase(RelationshipTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(RelationshipTypeId id) {

        RelationshipType relationshipType = repository.findById(id)
                .orElseThrow(() -> new RelationshipTypeNotFoundException(id));

        relationshipType.delete();
        repository.delete(relationshipType);

        eventPublisher.publish(relationshipType.domainEvents());
        relationshipType.clearDomainEvents();
    }
}
