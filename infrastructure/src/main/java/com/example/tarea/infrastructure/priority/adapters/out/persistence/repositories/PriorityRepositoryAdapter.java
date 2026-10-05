package com.example.tarea.infrastructure.priority.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.priority.model.aggregate.Priority;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;
import com.example.tarea.domain.priority.port.repository.PriorityRepository;
import com.example.tarea.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;
import com.example.tarea.infrastructure.priority.adapters.out.persistence.mappers.PriorityPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto PriorityRepository con Spring Data JPA.
 */
public class PriorityRepositoryAdapter implements PriorityRepository {

    private final PriorityJpaRepository jpaRepository;
    private final PriorityPersistenceMapper mapper;

    public PriorityRepositoryAdapter(PriorityJpaRepository jpaRepository, PriorityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Priority save(Priority priority) {
        PriorityJpaEntity saved = jpaRepository.save(mapper.toJpa(priority));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Priority> findById(PriorityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Priority> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Priority priority) {
        jpaRepository.deleteById(priority.id().value());
    }
}
