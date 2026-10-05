package com.example.tarea.application.gender.command;

import com.example.tarea.domain.gender.model.valueobject.GenderId;

public record UpdateGenderCommand(
        GenderId id,
        String description
) {
}
