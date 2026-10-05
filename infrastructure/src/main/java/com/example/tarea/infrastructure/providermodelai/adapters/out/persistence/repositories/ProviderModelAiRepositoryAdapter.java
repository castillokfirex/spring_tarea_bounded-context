package com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.example.tarea.domain.providermodelai.port.repository.ProviderModelAiRepository;
import com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.entity.ProviderModelAiJpaEntity;
import com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.mappers.ProviderModelAiPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ProviderModelAiRepository con Spring Data JPA.
 */
public class ProviderModelAiRepositoryAdapter implements ProviderModelAiRepository {

    private final ProviderModelAiJpaRepository jpaRepository;
    private final ProviderModelAiPersistenceMapper mapper;

    public ProviderModelAiRepositoryAdapter(ProviderModelAiJpaRepository jpaRepository, ProviderModelAiPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProviderModelAi save(ProviderModelAi providerModelAi) {
        ProviderModelAiJpaEntity saved = jpaRepository.save(mapper.toJpa(providerModelAi));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ProviderModelAi> findById(ProviderModelAiId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ProviderModelAi> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ProviderModelAi providerModelAi) {
        jpaRepository.deleteById(providerModelAi.id().value());
    }
}
