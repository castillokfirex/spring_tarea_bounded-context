package com.example.tarea.application.clinicalrecordstatus.usecase;

import java.util.List;

import com.example.tarea.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.example.tarea.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class ListClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public ListClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    public List<ClinicalRecordStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ClinicalRecordStatusResponse::fromDomain)
                .toList();
    }
}
