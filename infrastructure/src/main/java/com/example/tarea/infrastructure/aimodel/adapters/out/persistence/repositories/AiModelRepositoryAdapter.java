package com.example.tarea.infrastructure.aimodel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.aimodel.model.aggregate.AiModel;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;
import com.example.tarea.domain.aimodel.port.repository.AiModelRepository;
import com.example.tarea.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;
import com.example.tarea.infrastructure.aimodel.adapters.out.persistence.mappers.AiModelPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto AiModelRepository con Spring Data JPA.
 */
public class AiModelRepositoryAdapter implements AiModelRepository {

    private final AiModelJpaRepository jpaRepository;
    private final AiModelPersistenceMapper mapper;

    public AiModelRepositoryAdapter(AiModelJpaRepository jpaRepository, AiModelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AiModel save(AiModel aiModel) {
        AiModelJpaEntity saved = jpaRepository.save(mapper.toJpa(aiModel));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AiModel> findById(AiModelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AiModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(AiModel aiModel) {
        jpaRepository.deleteById(aiModel.id().value());
    }
}
