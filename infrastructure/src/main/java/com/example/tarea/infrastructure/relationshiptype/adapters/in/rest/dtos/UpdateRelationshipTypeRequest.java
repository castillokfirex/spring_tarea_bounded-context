package com.example.tarea.infrastructure.relationshiptype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateRelationshipTypeRequest(
        @NotBlank @Size(max = 50) String description
) {
}
