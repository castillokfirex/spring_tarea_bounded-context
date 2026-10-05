package com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.medicationroute.model.aggregate.MedicationRoute;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.example.tarea.domain.medicationroute.port.repository.MedicationRouteRepository;
import com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;
import com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.mappers.MedicationRoutePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto MedicationRouteRepository con Spring Data JPA.
 */
public class MedicationRouteRepositoryAdapter implements MedicationRouteRepository {

    private final MedicationRouteJpaRepository jpaRepository;
    private final MedicationRoutePersistenceMapper mapper;

    public MedicationRouteRepositoryAdapter(MedicationRouteJpaRepository jpaRepository, MedicationRoutePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MedicationRoute save(MedicationRoute medicationRoute) {
        MedicationRouteJpaEntity saved = jpaRepository.save(mapper.toJpa(medicationRoute));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MedicationRoute> findById(MedicationRouteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MedicationRoute> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(MedicationRoute medicationRoute) {
        jpaRepository.deleteById(medicationRoute.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, MedicationRouteId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, MedicationRouteId id) {
        return jpaRepository.existsByNameAndIdNot(name, id.value());
    }
}
