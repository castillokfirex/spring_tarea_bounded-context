package com.example.tarea.domain.priority.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.priority.model.aggregate.Priority;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;

/**
 * Puerto de salida (output port) para persistir el agregado Priority.
 */
public interface PriorityRepository {

    Priority save(Priority priority);

    Optional<Priority> findById(PriorityId id);

    List<Priority> findAll();

    void delete(Priority priority);
}
