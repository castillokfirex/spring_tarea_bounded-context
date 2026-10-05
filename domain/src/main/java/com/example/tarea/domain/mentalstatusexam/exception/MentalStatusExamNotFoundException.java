package com.example.tarea.domain.mentalstatusexam.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public class MentalStatusExamNotFoundException extends ResourceNotFoundException {

    public MentalStatusExamNotFoundException(MentalStatusExamId id) {
        super("MentalStatusExam with id '" + id.value() + "' was not found");
    }
}
