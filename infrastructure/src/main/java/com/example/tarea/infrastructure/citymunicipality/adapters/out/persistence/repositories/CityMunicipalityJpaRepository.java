package com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityMunicipalityJpaRepository extends JpaRepository<CityMunicipalityJpaEntity, UUID> {
}
