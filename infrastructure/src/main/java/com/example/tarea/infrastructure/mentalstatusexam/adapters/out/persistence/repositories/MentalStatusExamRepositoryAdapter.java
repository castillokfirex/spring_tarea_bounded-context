package com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.example.tarea.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.example.tarea.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;
import com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.mappers.MentalStatusExamPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto MentalStatusExamRepository con Spring Data JPA.
 */
public class MentalStatusExamRepositoryAdapter implements MentalStatusExamRepository {

    private final MentalStatusExamJpaRepository jpaRepository;
    private final MentalStatusExamPersistenceMapper mapper;

    public MentalStatusExamRepositoryAdapter(MentalStatusExamJpaRepository jpaRepository, MentalStatusExamPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MentalStatusExam save(MentalStatusExam mentalStatusExam) {
        MentalStatusExamJpaEntity saved = jpaRepository.save(mapper.toJpa(mentalStatusExam));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MentalStatusExam> findById(MentalStatusExamId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MentalStatusExam> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(MentalStatusExam mentalStatusExam) {
        jpaRepository.deleteById(mentalStatusExam.id().value());
    }
}
