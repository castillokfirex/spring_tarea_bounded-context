package com.example.tarea.infrastructure.encountertype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encountertype.model.aggregate.EncounterType;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;
import com.example.tarea.domain.encountertype.port.repository.EncounterTypeRepository;
import com.example.tarea.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;
import com.example.tarea.infrastructure.encountertype.adapters.out.persistence.mappers.EncounterTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto EncounterTypeRepository con Spring Data JPA.
 */
public class EncounterTypeRepositoryAdapter implements EncounterTypeRepository {

    private final EncounterTypeJpaRepository jpaRepository;
    private final EncounterTypePersistenceMapper mapper;

    public EncounterTypeRepositoryAdapter(EncounterTypeJpaRepository jpaRepository, EncounterTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterType save(EncounterType encounterType) {
        EncounterTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(encounterType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<EncounterType> findById(EncounterTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EncounterType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(EncounterType encounterType) {
        jpaRepository.deleteById(encounterType.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, EncounterTypeId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, EncounterTypeId id) {
        return jpaRepository.existsByNameAndIdNot(name, id.value());
    }
}
