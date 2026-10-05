package com.example.tarea.infrastructure.risklevel.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskLevelJpaRepository extends JpaRepository<RiskLevelJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}
