package com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.example.tarea.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;
import com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.mappers.ClinicalNotePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ClinicalNoteRepository con Spring Data JPA.
 */
public class ClinicalNoteRepositoryAdapter implements ClinicalNoteRepository {

    private final ClinicalNoteJpaRepository jpaRepository;
    private final ClinicalNotePersistenceMapper mapper;

    public ClinicalNoteRepositoryAdapter(ClinicalNoteJpaRepository jpaRepository, ClinicalNotePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalNote save(ClinicalNote clinicalNote) {
        ClinicalNoteJpaEntity saved = jpaRepository.save(mapper.toJpa(clinicalNote));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ClinicalNote> findById(ClinicalNoteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalNote> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ClinicalNote clinicalNote) {
        jpaRepository.deleteById(clinicalNote.id().value());
    }
}
