package com.example.tarea.infrastructure.contact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateContactRequest(
        @NotBlank @Size(max = 200) String fullName,
        @NotBlank @Size(max = 150) String email,
        @NotBlank String notes,
        @NotNull UUID cityId,
        UUID updatedBy
) {
}
