package com.example.tarea.domain.documenttype.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;

public class DocumentTypeNotFoundException extends ResourceNotFoundException {

    public DocumentTypeNotFoundException(DocumentTypeId id) {
        super("DocumentType with id '" + id.value() + "' was not found");
    }
}
