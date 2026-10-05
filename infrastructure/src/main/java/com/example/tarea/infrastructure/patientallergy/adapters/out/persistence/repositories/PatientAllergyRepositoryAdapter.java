package com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.patientallergy.model.aggregate.PatientAllergy;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.example.tarea.domain.patientallergy.port.repository.PatientAllergyRepository;
import com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;
import com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.mappers.PatientAllergyPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto PatientAllergyRepository con Spring Data JPA.
 */
public class PatientAllergyRepositoryAdapter implements PatientAllergyRepository {

    private final PatientAllergyJpaRepository jpaRepository;
    private final PatientAllergyPersistenceMapper mapper;

    public PatientAllergyRepositoryAdapter(PatientAllergyJpaRepository jpaRepository, PatientAllergyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientAllergy save(PatientAllergy patientAllergy) {
        PatientAllergyJpaEntity saved = jpaRepository.save(mapper.toJpa(patientAllergy));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<PatientAllergy> findById(PatientAllergyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<PatientAllergy> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(PatientAllergy patientAllergy) {
        jpaRepository.deleteById(patientAllergy.id().value());
    }
}
