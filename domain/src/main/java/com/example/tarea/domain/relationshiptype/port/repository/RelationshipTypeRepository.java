package com.example.tarea.domain.relationshiptype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.relationshiptype.model.aggregate.RelationshipType;
import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado RelationshipType.
 */
public interface RelationshipTypeRepository {

    RelationshipType save(RelationshipType relationshipType);

    Optional<RelationshipType> findById(RelationshipTypeId id);

    List<RelationshipType> findAll();

    void delete(RelationshipType relationshipType);

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdNot(String description, RelationshipTypeId id);
}
