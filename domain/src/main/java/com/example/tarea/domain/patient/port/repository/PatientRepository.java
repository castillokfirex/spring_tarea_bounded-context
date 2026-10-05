package com.example.tarea.domain.patient.port.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.tarea.domain.patient.model.aggregate.Patient;
import com.example.tarea.domain.patient.model.valueobject.PatientId;

/**
 * Puerto de salida (output port) para persistir el agregado Patient.
 */
public interface PatientRepository {

    Patient save(Patient patient);

    Optional<Patient> findById(PatientId id);

    List<Patient> findAll();

    void delete(Patient patient);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, PatientId id);

    boolean existsByDocumentTypeIdAndDocumentNumber(UUID documentTypeId, String documentNumber);

    boolean existsByDocumentTypeIdAndDocumentNumberAndIdNot(UUID documentTypeId, String documentNumber, PatientId id);
}
