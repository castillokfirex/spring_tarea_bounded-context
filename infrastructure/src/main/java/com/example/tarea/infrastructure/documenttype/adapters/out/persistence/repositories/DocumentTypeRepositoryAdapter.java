package com.example.tarea.infrastructure.documenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.documenttype.model.aggregate.DocumentType;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;
import com.example.tarea.domain.documenttype.port.repository.DocumentTypeRepository;
import com.example.tarea.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;
import com.example.tarea.infrastructure.documenttype.adapters.out.persistence.mappers.DocumentTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto DocumentTypeRepository con Spring Data JPA.
 */
public class DocumentTypeRepositoryAdapter implements DocumentTypeRepository {

    private final DocumentTypeJpaRepository jpaRepository;
    private final DocumentTypePersistenceMapper mapper;

    public DocumentTypeRepositoryAdapter(DocumentTypeJpaRepository jpaRepository, DocumentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DocumentType save(DocumentType documentType) {
        DocumentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(documentType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DocumentType> findById(DocumentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<DocumentType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DocumentType documentType) {
        jpaRepository.deleteById(documentType.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, DocumentTypeId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }
}
