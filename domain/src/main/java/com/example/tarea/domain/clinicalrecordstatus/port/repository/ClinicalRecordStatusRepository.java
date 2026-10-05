package com.example.tarea.domain.clinicalrecordstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

/**
 * Puerto de salida (output port) para persistir el agregado ClinicalRecordStatus.
 */
public interface ClinicalRecordStatusRepository {

    ClinicalRecordStatus save(ClinicalRecordStatus clinicalRecordStatus);

    Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id);

    List<ClinicalRecordStatus> findAll();

    void delete(ClinicalRecordStatus clinicalRecordStatus);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, ClinicalRecordStatusId id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, ClinicalRecordStatusId id);
}
