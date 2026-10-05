package com.example.tarea.domain.clinicalnote.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;

/**
 * Puerto de salida (output port) para persistir el agregado ClinicalNote.
 */
public interface ClinicalNoteRepository {

    ClinicalNote save(ClinicalNote clinicalNote);

    Optional<ClinicalNote> findById(ClinicalNoteId id);

    List<ClinicalNote> findAll();

    void delete(ClinicalNote clinicalNote);
}
