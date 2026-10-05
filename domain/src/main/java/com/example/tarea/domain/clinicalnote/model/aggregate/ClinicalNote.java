package com.example.tarea.domain.clinicalnote.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.clinicalnote.event.ClinicalNoteDeletedEvent;
import com.example.tarea.domain.clinicalnote.event.ClinicalNoteRegisteredEvent;
import com.example.tarea.domain.clinicalnote.event.ClinicalNoteUpdatedEvent;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>clinicalnote</b> (tabla <code>clinical_notes</code>).
 */
public class ClinicalNote extends AggregateRoot {

    private final ClinicalNoteId id;
    private UUID encounterId;
    private UUID professionalId;
    private String subjective;
    private String objective;
    private String assessment;
    private String plan;
    private String additionalNotes;
    private LocalDateTime signedAt;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClinicalNote(
            ClinicalNoteId id,
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.subjective = Guard.notBlank(subjective, "subjective");
        this.objective = Guard.notBlank(objective, "objective");
        this.assessment = Guard.notBlank(assessment, "assessment");
        this.plan = Guard.notBlank(plan, "plan");
        this.additionalNotes = Guard.notBlank(additionalNotes, "additionalNotes");
        this.signedAt = Guard.notNull(signedAt, "signedAt");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ClinicalNote register(
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt) {

        LocalDateTime now = LocalDateTime.now();
        ClinicalNote aggregate = new ClinicalNote(
                ClinicalNoteId.generate(),
                encounterId,
                professionalId,
                subjective,
                objective,
                assessment,
                plan,
                additionalNotes,
                signedAt,
                now,
                now);

        aggregate.recordEvent(new ClinicalNoteRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ClinicalNote restore(
            ClinicalNoteId id,
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClinicalNote(
                id,
                encounterId,
                professionalId,
                subjective,
                objective,
                assessment,
                plan,
                additionalNotes,
                signedAt,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt) {

        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.subjective = Guard.notBlank(subjective, "subjective");
        this.objective = Guard.notBlank(objective, "objective");
        this.assessment = Guard.notBlank(assessment, "assessment");
        this.plan = Guard.notBlank(plan, "plan");
        this.additionalNotes = Guard.notBlank(additionalNotes, "additionalNotes");
        this.signedAt = Guard.notNull(signedAt, "signedAt");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ClinicalNoteUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ClinicalNoteDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ClinicalNoteId id() {
        return id;
    }

    public UUID encounterId() {
        return encounterId;
    }

    public UUID professionalId() {
        return professionalId;
    }

    public String subjective() {
        return subjective;
    }

    public String objective() {
        return objective;
    }

    public String assessment() {
        return assessment;
    }

    public String plan() {
        return plan;
    }

    public String additionalNotes() {
        return additionalNotes;
    }

    public LocalDateTime signedAt() {
        return signedAt;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
