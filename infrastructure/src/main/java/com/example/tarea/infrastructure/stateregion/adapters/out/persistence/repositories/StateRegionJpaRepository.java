package com.example.tarea.infrastructure.stateregion.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRegionJpaRepository extends JpaRepository<StateRegionJpaEntity, UUID> {
}
