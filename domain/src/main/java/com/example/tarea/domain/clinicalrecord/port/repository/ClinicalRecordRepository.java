package com.example.tarea.domain.clinicalrecord.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

/**
 * Puerto de salida (output port) para persistir el agregado ClinicalRecord.
 */
public interface ClinicalRecordRepository {

    ClinicalRecord save(ClinicalRecord clinicalRecord);

    Optional<ClinicalRecord> findById(ClinicalRecordId id);

    List<ClinicalRecord> findAll();

    void delete(ClinicalRecord clinicalRecord);

    boolean existsByRecordNumber(String recordNumber);

    boolean existsByRecordNumberAndIdNot(String recordNumber, ClinicalRecordId id);
}
