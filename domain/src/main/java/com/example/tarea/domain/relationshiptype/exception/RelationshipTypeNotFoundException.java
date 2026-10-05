package com.example.tarea.domain.relationshiptype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class RelationshipTypeNotFoundException extends ResourceNotFoundException {

    public RelationshipTypeNotFoundException(RelationshipTypeId id) {
        super("RelationshipType with id '" + id.value() + "' was not found");
    }
}
