package com.example.tarea.domain.professionaltype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.professionaltype.model.aggregate.ProfessionalType;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado ProfessionalType.
 */
public interface ProfessionalTypeRepository {

    ProfessionalType save(ProfessionalType professionalType);

    Optional<ProfessionalType> findById(ProfessionalTypeId id);

    List<ProfessionalType> findAll();

    void delete(ProfessionalType professionalType);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, ProfessionalTypeId id);
}
