package com.example.tarea.infrastructure.documenttype.adapters.out.persistence.mappers;

import com.example.tarea.domain.documenttype.model.aggregate.DocumentType;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;
import com.example.tarea.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;

public class DocumentTypePersistenceMapper {

    public DocumentTypeJpaEntity toJpa(DocumentType domain) {

        if (domain == null) {
            return null;
        }

        DocumentTypeJpaEntity jpa = new DocumentTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public DocumentType toDomain(DocumentTypeJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return DocumentType.restore(
                new DocumentTypeId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
