package com.example.tarea.infrastructure.messagetype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateMessageTypeRequest(
        @NotBlank @Size(max = 50) String nameType
) {
}
