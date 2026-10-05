package com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.relationshiptype.model.aggregate.RelationshipType;
import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.example.tarea.domain.relationshiptype.port.repository.RelationshipTypeRepository;
import com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;
import com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.mappers.RelationshipTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto RelationshipTypeRepository con Spring Data JPA.
 */
public class RelationshipTypeRepositoryAdapter implements RelationshipTypeRepository {

    private final RelationshipTypeJpaRepository jpaRepository;
    private final RelationshipTypePersistenceMapper mapper;

    public RelationshipTypeRepositoryAdapter(RelationshipTypeJpaRepository jpaRepository, RelationshipTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RelationshipType save(RelationshipType relationshipType) {
        RelationshipTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(relationshipType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<RelationshipType> findById(RelationshipTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<RelationshipType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(RelationshipType relationshipType) {
        jpaRepository.deleteById(relationshipType.id().value());
    }

    @Override
    public boolean existsByDescription(String description) {
        return jpaRepository.existsByDescription(description);
    }

    @Override
    public boolean existsByDescriptionAndIdNot(String description, RelationshipTypeId id) {
        return jpaRepository.existsByDescriptionAndIdNot(description, id.value());
    }
}
