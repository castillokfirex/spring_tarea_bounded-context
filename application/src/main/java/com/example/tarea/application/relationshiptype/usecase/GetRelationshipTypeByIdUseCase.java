package com.example.tarea.application.relationshiptype.usecase;

import com.example.tarea.application.relationshiptype.dto.RelationshipTypeResponse;
import com.example.tarea.domain.relationshiptype.exception.RelationshipTypeNotFoundException;
import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.example.tarea.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class GetRelationshipTypeByIdUseCase {

    private final RelationshipTypeRepository repository;

    public GetRelationshipTypeByIdUseCase(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public RelationshipTypeResponse execute(RelationshipTypeId id) {
        return repository.findById(id)
                .map(RelationshipTypeResponse::fromDomain)
                .orElseThrow(() -> new RelationshipTypeNotFoundException(id));
    }
}
