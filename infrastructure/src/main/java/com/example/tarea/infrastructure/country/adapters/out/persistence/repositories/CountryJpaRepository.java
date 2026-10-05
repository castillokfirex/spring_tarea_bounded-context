package com.example.tarea.infrastructure.country.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<CountryJpaEntity, UUID> {
}
