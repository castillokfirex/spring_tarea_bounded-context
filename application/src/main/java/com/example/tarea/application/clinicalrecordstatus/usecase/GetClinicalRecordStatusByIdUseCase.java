package com.example.tarea.application.clinicalrecordstatus.usecase;

import com.example.tarea.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.example.tarea.domain.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundException;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.example.tarea.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class GetClinicalRecordStatusByIdUseCase {

    private final ClinicalRecordStatusRepository repository;

    public GetClinicalRecordStatusByIdUseCase(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordStatusResponse execute(ClinicalRecordStatusId id) {
        return repository.findById(id)
                .map(ClinicalRecordStatusResponse::fromDomain)
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundException(id));
    }
}
