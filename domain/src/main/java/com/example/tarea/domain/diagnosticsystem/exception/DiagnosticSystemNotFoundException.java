package com.example.tarea.domain.diagnosticsystem.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public class DiagnosticSystemNotFoundException extends ResourceNotFoundException {

    public DiagnosticSystemNotFoundException(DiagnosticSystemId id) {
        super("DiagnosticSystem with id '" + id.value() + "' was not found");
    }
}
