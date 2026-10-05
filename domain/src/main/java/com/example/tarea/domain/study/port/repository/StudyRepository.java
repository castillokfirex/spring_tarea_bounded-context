package com.example.tarea.domain.study.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.study.model.aggregate.Study;
import com.example.tarea.domain.study.model.valueobject.StudyId;

/**
 * Puerto de salida (output port) para persistir el agregado Study.
 */
public interface StudyRepository {

    Study save(Study study);

    Optional<Study> findById(StudyId id);

    List<Study> findAll();

    void delete(Study study);
}
