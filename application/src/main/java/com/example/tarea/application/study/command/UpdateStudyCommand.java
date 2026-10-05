package com.example.tarea.application.study.command;

import com.example.tarea.domain.study.model.valueobject.StudyId;

public record UpdateStudyCommand(
        StudyId id,
        String name
) {
}
