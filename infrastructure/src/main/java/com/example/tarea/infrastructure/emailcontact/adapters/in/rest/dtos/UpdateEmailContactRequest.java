package com.example.tarea.infrastructure.emailcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateEmailContactRequest(
        @NotNull UUID contactId,
        @NotBlank @Size(max = 150) String email,
        @NotBlank String notes
) {
}
