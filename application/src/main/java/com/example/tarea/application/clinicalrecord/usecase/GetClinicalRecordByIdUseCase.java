package com.example.tarea.application.clinicalrecord.usecase;

import com.example.tarea.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.example.tarea.domain.clinicalrecord.exception.ClinicalRecordNotFoundException;
import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.example.tarea.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class GetClinicalRecordByIdUseCase {

    private final ClinicalRecordRepository repository;

    public GetClinicalRecordByIdUseCase(ClinicalRecordRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordResponse execute(ClinicalRecordId id) {
        return repository.findById(id)
                .map(ClinicalRecordResponse::fromDomain)
                .orElseThrow(() -> new ClinicalRecordNotFoundException(id));
    }
}
