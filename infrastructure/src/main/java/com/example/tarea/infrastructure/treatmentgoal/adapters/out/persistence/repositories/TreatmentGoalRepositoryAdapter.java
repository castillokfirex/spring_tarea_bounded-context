package com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.example.tarea.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;
import com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.mappers.TreatmentGoalPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto TreatmentGoalRepository con Spring Data JPA.
 */
public class TreatmentGoalRepositoryAdapter implements TreatmentGoalRepository {

    private final TreatmentGoalJpaRepository jpaRepository;
    private final TreatmentGoalPersistenceMapper mapper;

    public TreatmentGoalRepositoryAdapter(TreatmentGoalJpaRepository jpaRepository, TreatmentGoalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentGoal save(TreatmentGoal treatmentGoal) {
        TreatmentGoalJpaEntity saved = jpaRepository.save(mapper.toJpa(treatmentGoal));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TreatmentGoal> findById(TreatmentGoalId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentGoal> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(TreatmentGoal treatmentGoal) {
        jpaRepository.deleteById(treatmentGoal.id().value());
    }
}
