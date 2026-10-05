package com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.example.tarea.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;
import com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.mappers.ClinicalRecordPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ClinicalRecordRepository con Spring Data JPA.
 */
public class ClinicalRecordRepositoryAdapter implements ClinicalRecordRepository {

    private final ClinicalRecordJpaRepository jpaRepository;
    private final ClinicalRecordPersistenceMapper mapper;

    public ClinicalRecordRepositoryAdapter(ClinicalRecordJpaRepository jpaRepository, ClinicalRecordPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalRecord save(ClinicalRecord clinicalRecord) {
        ClinicalRecordJpaEntity saved = jpaRepository.save(mapper.toJpa(clinicalRecord));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ClinicalRecord> findById(ClinicalRecordId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalRecord> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ClinicalRecord clinicalRecord) {
        jpaRepository.deleteById(clinicalRecord.id().value());
    }

    @Override
    public boolean existsByRecordNumber(String recordNumber) {
        return jpaRepository.existsByRecordNumber(recordNumber);
    }

    @Override
    public boolean existsByRecordNumberAndIdNot(String recordNumber, ClinicalRecordId id) {
        return jpaRepository.existsByRecordNumberAndIdNot(recordNumber, id.value());
    }
}
