package com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.example.tarea.domain.treatmentstatus.port.repository.TreatmentStatusRepository;
import com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;
import com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.mappers.TreatmentStatusPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto TreatmentStatusRepository con Spring Data JPA.
 */
public class TreatmentStatusRepositoryAdapter implements TreatmentStatusRepository {

    private final TreatmentStatusJpaRepository jpaRepository;
    private final TreatmentStatusPersistenceMapper mapper;

    public TreatmentStatusRepositoryAdapter(TreatmentStatusJpaRepository jpaRepository, TreatmentStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentStatus save(TreatmentStatus treatmentStatus) {
        TreatmentStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(treatmentStatus));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TreatmentStatus> findById(TreatmentStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentStatus> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(TreatmentStatus treatmentStatus) {
        jpaRepository.deleteById(treatmentStatus.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, TreatmentStatusId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, TreatmentStatusId id) {
        return jpaRepository.existsByNameAndIdNot(name, id.value());
    }
}
