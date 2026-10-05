package com.example.tarea.domain.professionalstudy.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

/**
 * Puerto de salida (output port) para persistir el agregado ProfessionalStudy.
 */
public interface ProfessionalStudyRepository {

    ProfessionalStudy save(ProfessionalStudy professionalStudy);

    Optional<ProfessionalStudy> findById(ProfessionalStudyId id);

    List<ProfessionalStudy> findAll();

    void delete(ProfessionalStudy professionalStudy);
}
