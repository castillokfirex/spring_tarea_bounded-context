package com.example.tarea.domain.consenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.consenttype.model.aggregate.ConsentType;
import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado ConsentType.
 */
public interface ConsentTypeRepository {

    ConsentType save(ConsentType consentType);

    Optional<ConsentType> findById(ConsentTypeId id);

    List<ConsentType> findAll();

    void delete(ConsentType consentType);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, ConsentTypeId id);
}
