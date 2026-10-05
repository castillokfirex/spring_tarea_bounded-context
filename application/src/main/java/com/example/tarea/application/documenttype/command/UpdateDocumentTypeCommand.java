package com.example.tarea.application.documenttype.command;

import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;

public record UpdateDocumentTypeCommand(
        DocumentTypeId id,
        String code,
        String name,
        Boolean active
) {
}
