package com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.airunstatus.model.aggregate.AiRunStatus;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.example.tarea.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;
import com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.mappers.AiRunStatusPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto AiRunStatusRepository con Spring Data JPA.
 */
public class AiRunStatusRepositoryAdapter implements AiRunStatusRepository {

    private final AiRunStatusJpaRepository jpaRepository;
    private final AiRunStatusPersistenceMapper mapper;

    public AiRunStatusRepositoryAdapter(AiRunStatusJpaRepository jpaRepository, AiRunStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AiRunStatus save(AiRunStatus aiRunStatus) {
        AiRunStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aiRunStatus));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AiRunStatus> findById(AiRunStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AiRunStatus> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(AiRunStatus aiRunStatus) {
        jpaRepository.deleteById(aiRunStatus.id().value());
    }
}
