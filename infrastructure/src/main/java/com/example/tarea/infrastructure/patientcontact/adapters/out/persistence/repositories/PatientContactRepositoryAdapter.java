package com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.patientcontact.model.aggregate.PatientContact;
import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;
import com.example.tarea.domain.patientcontact.port.repository.PatientContactRepository;
import com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;
import com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.mappers.PatientContactPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto PatientContactRepository con Spring Data JPA.
 */
public class PatientContactRepositoryAdapter implements PatientContactRepository {

    private final PatientContactJpaRepository jpaRepository;
    private final PatientContactPersistenceMapper mapper;

    public PatientContactRepositoryAdapter(PatientContactJpaRepository jpaRepository, PatientContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientContact save(PatientContact patientContact) {
        PatientContactJpaEntity saved = jpaRepository.save(mapper.toJpa(patientContact));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<PatientContact> findById(PatientContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<PatientContact> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(PatientContact patientContact) {
        jpaRepository.deleteById(patientContact.id().value());
    }
}
