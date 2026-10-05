package com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.example.tarea.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;
import com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.mappers.ProfessionalStudyPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ProfessionalStudyRepository con Spring Data JPA.
 */
public class ProfessionalStudyRepositoryAdapter implements ProfessionalStudyRepository {

    private final ProfessionalStudyJpaRepository jpaRepository;
    private final ProfessionalStudyPersistenceMapper mapper;

    public ProfessionalStudyRepositoryAdapter(ProfessionalStudyJpaRepository jpaRepository, ProfessionalStudyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalStudy save(ProfessionalStudy professionalStudy) {
        ProfessionalStudyJpaEntity saved = jpaRepository.save(mapper.toJpa(professionalStudy));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ProfessionalStudy> findById(ProfessionalStudyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ProfessionalStudy> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ProfessionalStudy professionalStudy) {
        jpaRepository.deleteById(professionalStudy.id().value());
    }
}
