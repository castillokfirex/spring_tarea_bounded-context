package com.example.tarea.domain.professional.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.professional.model.aggregate.Professional;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;

/**
 * Puerto de salida (output port) para persistir el agregado Professional.
 */
public interface ProfessionalRepository {

    Professional save(Professional professional);

    Optional<Professional> findById(ProfessionalId id);

    List<Professional> findAll();

    void delete(Professional professional);

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumberAndIdNot(String documentNumber, ProfessionalId id);

    boolean existsByFirstName(String firstName);

    boolean existsByFirstNameAndIdNot(String firstName, ProfessionalId id);

    boolean existsByLastName(String lastName);

    boolean existsByLastNameAndIdNot(String lastName, ProfessionalId id);

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByLicenseNumberAndIdNot(String licenseNumber, ProfessionalId id);
}
