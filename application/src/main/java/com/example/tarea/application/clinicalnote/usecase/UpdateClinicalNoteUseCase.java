package com.example.tarea.application.clinicalnote.usecase;

import com.example.tarea.application.clinicalnote.command.UpdateClinicalNoteCommand;
import com.example.tarea.application.clinicalnote.dto.ClinicalNoteResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalnote.exception.ClinicalNoteNotFoundException;
import com.example.tarea.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.example.tarea.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class UpdateClinicalNoteUseCase {

    private final ClinicalNoteRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateClinicalNoteUseCase(ClinicalNoteRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ClinicalNoteResponse execute(UpdateClinicalNoteCommand command) {

        ClinicalNote clinicalNote = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalNoteNotFoundException(command.id()));

        clinicalNote.update(
                command.encounterId(),
                command.professionalId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.additionalNotes(),
                command.signedAt());

        ClinicalNote saved = repository.save(clinicalNote);

        eventPublisher.publish(clinicalNote.domainEvents());
        clinicalNote.clearDomainEvents();

        return ClinicalNoteResponse.fromDomain(saved);
    }
}
