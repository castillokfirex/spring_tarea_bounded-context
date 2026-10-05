package com.example.tarea.domain.patientallergy.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.patientallergy.model.aggregate.PatientAllergy;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;

/**
 * Puerto de salida (output port) para persistir el agregado PatientAllergy.
 */
public interface PatientAllergyRepository {

    PatientAllergy save(PatientAllergy patientAllergy);

    Optional<PatientAllergy> findById(PatientAllergyId id);

    List<PatientAllergy> findAll();

    void delete(PatientAllergy patientAllergy);
}
