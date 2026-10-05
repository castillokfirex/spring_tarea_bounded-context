package com.example.tarea.domain.gender.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.gender.model.aggregate.Gender;
import com.example.tarea.domain.gender.model.valueobject.GenderId;

/**
 * Puerto de salida (output port) para persistir el agregado Gender.
 */
public interface GenderRepository {

    Gender save(Gender gender);

    Optional<Gender> findById(GenderId id);

    List<Gender> findAll();

    void delete(Gender gender);

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdNot(String description, GenderId id);
}
