package com.example.tarea.domain.documenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.documenttype.model.aggregate.DocumentType;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado DocumentType.
 */
public interface DocumentTypeRepository {

    DocumentType save(DocumentType documentType);

    Optional<DocumentType> findById(DocumentTypeId id);

    List<DocumentType> findAll();

    void delete(DocumentType documentType);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, DocumentTypeId id);
}
