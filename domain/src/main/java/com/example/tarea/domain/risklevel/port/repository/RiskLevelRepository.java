package com.example.tarea.domain.risklevel.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.risklevel.model.aggregate.RiskLevel;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;

/**
 * Puerto de salida (output port) para persistir el agregado RiskLevel.
 */
public interface RiskLevelRepository {

    RiskLevel save(RiskLevel riskLevel);

    Optional<RiskLevel> findById(RiskLevelId id);

    List<RiskLevel> findAll();

    void delete(RiskLevel riskLevel);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, RiskLevelId id);
}
