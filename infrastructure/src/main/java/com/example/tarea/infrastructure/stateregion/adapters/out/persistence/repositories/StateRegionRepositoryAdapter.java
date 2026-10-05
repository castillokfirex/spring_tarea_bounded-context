package com.example.tarea.infrastructure.stateregion.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.stateregion.model.aggregate.StateRegion;
import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;
import com.example.tarea.domain.stateregion.port.repository.StateRegionRepository;
import com.example.tarea.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;
import com.example.tarea.infrastructure.stateregion.adapters.out.persistence.mappers.StateRegionPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto StateRegionRepository con Spring Data JPA.
 */
public class StateRegionRepositoryAdapter implements StateRegionRepository {

    private final StateRegionJpaRepository jpaRepository;
    private final StateRegionPersistenceMapper mapper;

    public StateRegionRepositoryAdapter(StateRegionJpaRepository jpaRepository, StateRegionPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public StateRegion save(StateRegion stateRegion) {
        StateRegionJpaEntity saved = jpaRepository.save(mapper.toJpa(stateRegion));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<StateRegion> findById(StateRegionId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<StateRegion> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(StateRegion stateRegion) {
        jpaRepository.deleteById(stateRegion.id().value());
    }
}
