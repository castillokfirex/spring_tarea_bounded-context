package com.example.tarea.domain.medicationroute.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.medicationroute.model.aggregate.MedicationRoute;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;

/**
 * Puerto de salida (output port) para persistir el agregado MedicationRoute.
 */
public interface MedicationRouteRepository {

    MedicationRoute save(MedicationRoute medicationRoute);

    Optional<MedicationRoute> findById(MedicationRouteId id);

    List<MedicationRoute> findAll();

    void delete(MedicationRoute medicationRoute);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, MedicationRouteId id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, MedicationRouteId id);
}
