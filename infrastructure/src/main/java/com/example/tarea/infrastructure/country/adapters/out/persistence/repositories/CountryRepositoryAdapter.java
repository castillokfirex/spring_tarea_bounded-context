package com.example.tarea.infrastructure.country.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.country.model.aggregate.Country;
import com.example.tarea.domain.country.model.valueobject.CountryId;
import com.example.tarea.domain.country.port.repository.CountryRepository;
import com.example.tarea.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;
import com.example.tarea.infrastructure.country.adapters.out.persistence.mappers.CountryPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto CountryRepository con Spring Data JPA.
 */
public class CountryRepositoryAdapter implements CountryRepository {

    private final CountryJpaRepository jpaRepository;
    private final CountryPersistenceMapper mapper;

    public CountryRepositoryAdapter(CountryJpaRepository jpaRepository, CountryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Country save(Country country) {
        CountryJpaEntity saved = jpaRepository.save(mapper.toJpa(country));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Country> findById(CountryId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Country> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Country country) {
        jpaRepository.deleteById(country.id().value());
    }
}
