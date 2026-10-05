package com.example.tarea.application.assessmenttype.command;

public record RegisterAssessmentTypeCommand(
        String code,
        String name,
        Boolean active,
        String description
) {
}
