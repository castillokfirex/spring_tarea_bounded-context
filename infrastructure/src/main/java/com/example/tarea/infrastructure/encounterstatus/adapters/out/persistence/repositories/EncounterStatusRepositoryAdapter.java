package com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.example.tarea.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.example.tarea.domain.encounterstatus.port.repository.EncounterStatusRepository;
import com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;
import com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.mappers.EncounterStatusPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto EncounterStatusRepository con Spring Data JPA.
 */
public class EncounterStatusRepositoryAdapter implements EncounterStatusRepository {

    private final EncounterStatusJpaRepository jpaRepository;
    private final EncounterStatusPersistenceMapper mapper;

    public EncounterStatusRepositoryAdapter(EncounterStatusJpaRepository jpaRepository, EncounterStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterStatus save(EncounterStatus encounterStatus) {
        EncounterStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(encounterStatus));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<EncounterStatus> findById(EncounterStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EncounterStatus> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(EncounterStatus encounterStatus) {
        jpaRepository.deleteById(encounterStatus.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, EncounterStatusId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, EncounterStatusId id) {
        return jpaRepository.existsByNameAndIdNot(name, id.value());
    }
}
