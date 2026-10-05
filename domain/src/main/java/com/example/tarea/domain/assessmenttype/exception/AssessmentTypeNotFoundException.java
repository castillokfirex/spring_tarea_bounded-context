package com.example.tarea.domain.assessmenttype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public class AssessmentTypeNotFoundException extends ResourceNotFoundException {

    public AssessmentTypeNotFoundException(AssessmentTypeId id) {
        super("AssessmentType with id '" + id.value() + "' was not found");
    }
}
