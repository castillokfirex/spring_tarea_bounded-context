package com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encountermodality.model.aggregate.EncounterModality;
import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.example.tarea.domain.encountermodality.port.repository.EncounterModalityRepository;
import com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;
import com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.mappers.EncounterModalityPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto EncounterModalityRepository con Spring Data JPA.
 */
public class EncounterModalityRepositoryAdapter implements EncounterModalityRepository {

    private final EncounterModalityJpaRepository jpaRepository;
    private final EncounterModalityPersistenceMapper mapper;

    public EncounterModalityRepositoryAdapter(EncounterModalityJpaRepository jpaRepository, EncounterModalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterModality save(EncounterModality encounterModality) {
        EncounterModalityJpaEntity saved = jpaRepository.save(mapper.toJpa(encounterModality));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<EncounterModality> findById(EncounterModalityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EncounterModality> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(EncounterModality encounterModality) {
        jpaRepository.deleteById(encounterModality.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, EncounterModalityId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }
}
