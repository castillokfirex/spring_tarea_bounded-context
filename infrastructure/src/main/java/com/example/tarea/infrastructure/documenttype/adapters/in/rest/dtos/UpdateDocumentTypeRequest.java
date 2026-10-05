package com.example.tarea.infrastructure.documenttype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDocumentTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active
) {
}
