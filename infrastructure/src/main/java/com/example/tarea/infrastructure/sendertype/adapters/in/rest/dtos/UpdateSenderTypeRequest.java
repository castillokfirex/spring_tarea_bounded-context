package com.example.tarea.infrastructure.sendertype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateSenderTypeRequest(
        @NotBlank @Size(max = 50) String nameType
) {
}
