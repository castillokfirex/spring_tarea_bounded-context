package com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.example.tarea.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;
import com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.mappers.CityMunicipalityPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto CityMunicipalityRepository con Spring Data JPA.
 */
public class CityMunicipalityRepositoryAdapter implements CityMunicipalityRepository {

    private final CityMunicipalityJpaRepository jpaRepository;
    private final CityMunicipalityPersistenceMapper mapper;

    public CityMunicipalityRepositoryAdapter(CityMunicipalityJpaRepository jpaRepository, CityMunicipalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CityMunicipality save(CityMunicipality cityMunicipality) {
        CityMunicipalityJpaEntity saved = jpaRepository.save(mapper.toJpa(cityMunicipality));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CityMunicipality> findById(CityMunicipalityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<CityMunicipality> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(CityMunicipality cityMunicipality) {
        jpaRepository.deleteById(cityMunicipality.id().value());
    }
}
