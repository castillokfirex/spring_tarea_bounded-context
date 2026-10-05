package com.example.tarea.domain.patientcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.patientcontact.model.aggregate.PatientContact;
import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;

/**
 * Puerto de salida (output port) para persistir el agregado PatientContact.
 */
public interface PatientContactRepository {

    PatientContact save(PatientContact patientContact);

    Optional<PatientContact> findById(PatientContactId id);

    List<PatientContact> findAll();

    void delete(PatientContact patientContact);
}
