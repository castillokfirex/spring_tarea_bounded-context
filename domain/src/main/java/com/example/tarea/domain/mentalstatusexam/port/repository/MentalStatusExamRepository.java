package com.example.tarea.domain.mentalstatusexam.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.example.tarea.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

/**
 * Puerto de salida (output port) para persistir el agregado MentalStatusExam.
 */
public interface MentalStatusExamRepository {

    MentalStatusExam save(MentalStatusExam mentalStatusExam);

    Optional<MentalStatusExam> findById(MentalStatusExamId id);

    List<MentalStatusExam> findAll();

    void delete(MentalStatusExam mentalStatusExam);
}
