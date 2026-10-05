package com.example.tarea.application.clinicalnote.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalnote.exception.ClinicalNoteNotFoundException;
import com.example.tarea.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.example.tarea.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class DeleteClinicalNoteUseCase {

    private final ClinicalNoteRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteClinicalNoteUseCase(ClinicalNoteRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ClinicalNoteId id) {

        ClinicalNote clinicalNote = repository.findById(id)
                .orElseThrow(() -> new ClinicalNoteNotFoundException(id));

        clinicalNote.delete();
        repository.delete(clinicalNote);

        eventPublisher.publish(clinicalNote.domainEvents());
        clinicalNote.clearDomainEvents();
    }
}
