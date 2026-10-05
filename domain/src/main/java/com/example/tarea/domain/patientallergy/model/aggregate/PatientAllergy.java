package com.example.tarea.domain.patientallergy.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.patientallergy.event.PatientAllergyDeletedEvent;
import com.example.tarea.domain.patientallergy.event.PatientAllergyRegisteredEvent;
import com.example.tarea.domain.patientallergy.event.PatientAllergyUpdatedEvent;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;

/**
 * Aggregate root del bounded context <b>patientallergy</b> (tabla <code>patient_allergies</code>).
 */
public class PatientAllergy extends AggregateRoot {

    private final PatientAllergyId id;
    private UUID patientId;
    private String substance;
    private String reaction;
    private String severity;
    private Boolean active;
    private LocalDateTime recordedAt;
    private UUID recordedBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PatientAllergy(
            PatientAllergyId id,
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            Boolean active,
            LocalDateTime recordedAt,
            UUID recordedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.patientId = Guard.notNull(patientId, "patientId");
        this.substance = Guard.maxLength(Guard.notBlank(substance, "substance"), 200, "substance");
        this.reaction = reaction;
        this.severity = Guard.maxLength(Guard.notBlank(severity, "severity"), 20, "severity");
        this.active = Guard.notNull(active, "active");
        this.recordedAt = Guard.notNull(recordedAt, "recordedAt");
        this.recordedBy = Guard.notNull(recordedBy, "recordedBy");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static PatientAllergy register(
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            Boolean active,
            LocalDateTime recordedAt,
            UUID recordedBy) {

        LocalDateTime now = LocalDateTime.now();
        PatientAllergy aggregate = new PatientAllergy(
                PatientAllergyId.generate(),
                patientId,
                substance,
                reaction,
                severity,
                active,
                recordedAt != null ? recordedAt : now,
                recordedBy,
                now,
                now);

        aggregate.recordEvent(new PatientAllergyRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static PatientAllergy restore(
            PatientAllergyId id,
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            Boolean active,
            LocalDateTime recordedAt,
            UUID recordedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new PatientAllergy(
                id,
                patientId,
                substance,
                reaction,
                severity,
                active,
                recordedAt,
                recordedBy,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            Boolean active,
            LocalDateTime recordedAt,
            UUID recordedBy) {

        this.patientId = Guard.notNull(patientId, "patientId");
        this.substance = Guard.maxLength(Guard.notBlank(substance, "substance"), 200, "substance");
        this.reaction = reaction;
        this.severity = Guard.maxLength(Guard.notBlank(severity, "severity"), 20, "severity");
        this.active = Guard.notNull(active, "active");
        this.recordedAt = recordedAt != null ? recordedAt : this.recordedAt;
        this.recordedBy = Guard.notNull(recordedBy, "recordedBy");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new PatientAllergyUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new PatientAllergyDeletedEvent(this.id, LocalDateTime.now()));
    }

    public PatientAllergyId id() {
        return id;
    }

    public UUID patientId() {
        return patientId;
    }

    public String substance() {
        return substance;
    }

    public String reaction() {
        return reaction;
    }

    public String severity() {
        return severity;
    }

    public Boolean active() {
        return active;
    }

    public LocalDateTime recordedAt() {
        return recordedAt;
    }

    public UUID recordedBy() {
        return recordedBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
