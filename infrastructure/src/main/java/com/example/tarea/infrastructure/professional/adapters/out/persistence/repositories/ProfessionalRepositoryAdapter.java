package com.example.tarea.infrastructure.professional.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.professional.model.aggregate.Professional;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;
import com.example.tarea.domain.professional.port.repository.ProfessionalRepository;
import com.example.tarea.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;
import com.example.tarea.infrastructure.professional.adapters.out.persistence.mappers.ProfessionalPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ProfessionalRepository con Spring Data JPA.
 */
public class ProfessionalRepositoryAdapter implements ProfessionalRepository {

    private final ProfessionalJpaRepository jpaRepository;
    private final ProfessionalPersistenceMapper mapper;

    public ProfessionalRepositoryAdapter(ProfessionalJpaRepository jpaRepository, ProfessionalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Professional save(Professional professional) {
        ProfessionalJpaEntity saved = jpaRepository.save(mapper.toJpa(professional));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Professional> findById(ProfessionalId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Professional> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Professional professional) {
        jpaRepository.deleteById(professional.id().value());
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return jpaRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByDocumentNumberAndIdNot(String documentNumber, ProfessionalId id) {
        return jpaRepository.existsByDocumentNumberAndIdNot(documentNumber, id.value());
    }

    @Override
    public boolean existsByFirstName(String firstName) {
        return jpaRepository.existsByFirstName(firstName);
    }

    @Override
    public boolean existsByFirstNameAndIdNot(String firstName, ProfessionalId id) {
        return jpaRepository.existsByFirstNameAndIdNot(firstName, id.value());
    }

    @Override
    public boolean existsByLastName(String lastName) {
        return jpaRepository.existsByLastName(lastName);
    }

    @Override
    public boolean existsByLastNameAndIdNot(String lastName, ProfessionalId id) {
        return jpaRepository.existsByLastNameAndIdNot(lastName, id.value());
    }

    @Override
    public boolean existsByLicenseNumber(String licenseNumber) {
        return jpaRepository.existsByLicenseNumber(licenseNumber);
    }

    @Override
    public boolean existsByLicenseNumberAndIdNot(String licenseNumber, ProfessionalId id) {
        return jpaRepository.existsByLicenseNumberAndIdNot(licenseNumber, id.value());
    }
}
