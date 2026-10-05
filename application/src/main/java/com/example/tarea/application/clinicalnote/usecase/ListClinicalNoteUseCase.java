package com.example.tarea.application.clinicalnote.usecase;

import java.util.List;

import com.example.tarea.application.clinicalnote.dto.ClinicalNoteResponse;
import com.example.tarea.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class ListClinicalNoteUseCase {

    private final ClinicalNoteRepository repository;

    public ListClinicalNoteUseCase(ClinicalNoteRepository repository) {
        this.repository = repository;
    }

    public List<ClinicalNoteResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ClinicalNoteResponse::fromDomain)
                .toList();
    }
}
