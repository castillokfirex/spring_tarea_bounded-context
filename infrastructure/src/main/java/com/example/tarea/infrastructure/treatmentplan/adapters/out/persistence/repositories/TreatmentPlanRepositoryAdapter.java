package com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.example.tarea.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;
import com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.mappers.TreatmentPlanPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto TreatmentPlanRepository con Spring Data JPA.
 */
public class TreatmentPlanRepositoryAdapter implements TreatmentPlanRepository {

    private final TreatmentPlanJpaRepository jpaRepository;
    private final TreatmentPlanPersistenceMapper mapper;

    public TreatmentPlanRepositoryAdapter(TreatmentPlanJpaRepository jpaRepository, TreatmentPlanPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentPlan save(TreatmentPlan treatmentPlan) {
        TreatmentPlanJpaEntity saved = jpaRepository.save(mapper.toJpa(treatmentPlan));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TreatmentPlan> findById(TreatmentPlanId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentPlan> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(TreatmentPlan treatmentPlan) {
        jpaRepository.deleteById(treatmentPlan.id().value());
    }
}
