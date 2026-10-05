package com.example.tarea.infrastructure.patient.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.tarea.domain.patient.model.aggregate.Patient;
import com.example.tarea.domain.patient.model.valueobject.PatientId;
import com.example.tarea.domain.patient.port.repository.PatientRepository;
import com.example.tarea.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;
import com.example.tarea.infrastructure.patient.adapters.out.persistence.mappers.PatientPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto PatientRepository con Spring Data JPA.
 */
public class PatientRepositoryAdapter implements PatientRepository {

    private final PatientJpaRepository jpaRepository;
    private final PatientPersistenceMapper mapper;

    public PatientRepositoryAdapter(PatientJpaRepository jpaRepository, PatientPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Patient save(Patient patient) {
        PatientJpaEntity saved = jpaRepository.save(mapper.toJpa(patient));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Patient> findById(PatientId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Patient> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Patient patient) {
        jpaRepository.deleteById(patient.id().value());
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, PatientId id) {
        return jpaRepository.existsByEmailAndIdNot(email, id.value());
    }

    @Override
    public boolean existsByDocumentTypeIdAndDocumentNumber(UUID documentTypeId, String documentNumber) {
        return jpaRepository.existsByDocumentTypeIdAndDocumentNumber(documentTypeId, documentNumber);
    }

    @Override
    public boolean existsByDocumentTypeIdAndDocumentNumberAndIdNot(UUID documentTypeId, String documentNumber, PatientId id) {
        return jpaRepository.existsByDocumentTypeIdAndDocumentNumberAndIdNot(documentTypeId, documentNumber, id.value());
    }
}
