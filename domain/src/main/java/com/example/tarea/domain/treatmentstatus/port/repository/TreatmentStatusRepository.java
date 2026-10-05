package com.example.tarea.domain.treatmentstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

/**
 * Puerto de salida (output port) para persistir el agregado TreatmentStatus.
 */
public interface TreatmentStatusRepository {

    TreatmentStatus save(TreatmentStatus treatmentStatus);

    Optional<TreatmentStatus> findById(TreatmentStatusId id);

    List<TreatmentStatus> findAll();

    void delete(TreatmentStatus treatmentStatus);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, TreatmentStatusId id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, TreatmentStatusId id);
}
