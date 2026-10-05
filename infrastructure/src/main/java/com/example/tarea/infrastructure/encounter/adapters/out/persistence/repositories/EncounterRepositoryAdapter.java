package com.example.tarea.infrastructure.encounter.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encounter.model.aggregate.Encounter;
import com.example.tarea.domain.encounter.model.valueobject.EncounterId;
import com.example.tarea.domain.encounter.port.repository.EncounterRepository;
import com.example.tarea.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;
import com.example.tarea.infrastructure.encounter.adapters.out.persistence.mappers.EncounterPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto EncounterRepository con Spring Data JPA.
 */
public class EncounterRepositoryAdapter implements EncounterRepository {

    private final EncounterJpaRepository jpaRepository;
    private final EncounterPersistenceMapper mapper;

    public EncounterRepositoryAdapter(EncounterJpaRepository jpaRepository, EncounterPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Encounter save(Encounter encounter) {
        EncounterJpaEntity saved = jpaRepository.save(mapper.toJpa(encounter));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Encounter> findById(EncounterId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Encounter> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Encounter encounter) {
        jpaRepository.deleteById(encounter.id().value());
    }
}
