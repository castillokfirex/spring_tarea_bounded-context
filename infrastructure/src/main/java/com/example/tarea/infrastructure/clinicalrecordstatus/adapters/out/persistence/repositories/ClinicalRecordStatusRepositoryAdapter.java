package com.example.tarea.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.example.tarea.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import com.example.tarea.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;
import com.example.tarea.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers.ClinicalRecordStatusPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ClinicalRecordStatusRepository con Spring Data JPA.
 */
public class ClinicalRecordStatusRepositoryAdapter implements ClinicalRecordStatusRepository {

    private final ClinicalRecordStatusJpaRepository jpaRepository;
    private final ClinicalRecordStatusPersistenceMapper mapper;

    public ClinicalRecordStatusRepositoryAdapter(ClinicalRecordStatusJpaRepository jpaRepository, ClinicalRecordStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalRecordStatus save(ClinicalRecordStatus clinicalRecordStatus) {
        ClinicalRecordStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(clinicalRecordStatus));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalRecordStatus> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ClinicalRecordStatus clinicalRecordStatus) {
        jpaRepository.deleteById(clinicalRecordStatus.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, ClinicalRecordStatusId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, ClinicalRecordStatusId id) {
        return jpaRepository.existsByNameAndIdNot(name, id.value());
    }
}
