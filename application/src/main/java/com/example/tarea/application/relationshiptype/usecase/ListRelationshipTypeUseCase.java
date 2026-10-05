package com.example.tarea.application.relationshiptype.usecase;

import java.util.List;

import com.example.tarea.application.relationshiptype.dto.RelationshipTypeResponse;
import com.example.tarea.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class ListRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;

    public ListRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public List<RelationshipTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(RelationshipTypeResponse::fromDomain)
                .toList();
    }
}
