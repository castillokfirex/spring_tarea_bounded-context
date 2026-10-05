package com.example.tarea.application.clinicalnote.usecase;

import com.example.tarea.application.clinicalnote.dto.ClinicalNoteResponse;
import com.example.tarea.domain.clinicalnote.exception.ClinicalNoteNotFoundException;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.example.tarea.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class GetClinicalNoteByIdUseCase {

    private final ClinicalNoteRepository repository;

    public GetClinicalNoteByIdUseCase(ClinicalNoteRepository repository) {
        this.repository = repository;
    }

    public ClinicalNoteResponse execute(ClinicalNoteId id) {
        return repository.findById(id)
                .map(ClinicalNoteResponse::fromDomain)
                .orElseThrow(() -> new ClinicalNoteNotFoundException(id));
    }
}
