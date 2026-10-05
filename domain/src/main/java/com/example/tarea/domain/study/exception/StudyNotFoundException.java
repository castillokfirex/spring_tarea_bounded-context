package com.example.tarea.domain.study.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.study.model.valueobject.StudyId;

public class StudyNotFoundException extends ResourceNotFoundException {

    public StudyNotFoundException(StudyId id) {
        super("Study with id '" + id.value() + "' was not found");
    }
}
