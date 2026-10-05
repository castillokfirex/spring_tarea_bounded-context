package com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.example.tarea.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;
import com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers.TreatmentGoalStatusPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto TreatmentGoalStatusRepository con Spring Data JPA.
 */
public class TreatmentGoalStatusRepositoryAdapter implements TreatmentGoalStatusRepository {

    private final TreatmentGoalStatusJpaRepository jpaRepository;
    private final TreatmentGoalStatusPersistenceMapper mapper;

    public TreatmentGoalStatusRepositoryAdapter(TreatmentGoalStatusJpaRepository jpaRepository, TreatmentGoalStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentGoalStatus save(TreatmentGoalStatus treatmentGoalStatus) {
        TreatmentGoalStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(treatmentGoalStatus));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentGoalStatus> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(TreatmentGoalStatus treatmentGoalStatus) {
        jpaRepository.deleteById(treatmentGoalStatus.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, TreatmentGoalStatusId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, TreatmentGoalStatusId id) {
        return jpaRepository.existsByNameAndIdNot(name, id.value());
    }
}
