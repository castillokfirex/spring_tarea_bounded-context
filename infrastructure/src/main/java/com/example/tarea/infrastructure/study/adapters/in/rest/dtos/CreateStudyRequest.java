package com.example.tarea.infrastructure.study.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateStudyRequest(
        @NotBlank @Size(max = 40) String name
) {
}
