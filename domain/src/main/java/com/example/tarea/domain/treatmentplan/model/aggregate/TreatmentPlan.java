package com.example.tarea.domain.treatmentplan.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.treatmentplan.event.TreatmentPlanDeletedEvent;
import com.example.tarea.domain.treatmentplan.event.TreatmentPlanRegisteredEvent;
import com.example.tarea.domain.treatmentplan.event.TreatmentPlanUpdatedEvent;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;

/**
 * Aggregate root del bounded context <b>treatmentplan</b> (tabla <code>treatment_plans</code>).
 */
public class TreatmentPlan extends AggregateRoot {

    private final TreatmentPlanId id;
    private UUID encounterId;
    private UUID professionalId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID treatmentStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentPlan(
            TreatmentPlanId id,
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.title = Guard.maxLength(Guard.notBlank(title, "title"), 200, "title");
        this.description = Guard.notBlank(description, "description");
        this.startDate = Guard.notNull(startDate, "startDate");
        this.endDate = Guard.notNull(endDate, "endDate");
        this.treatmentStatusId = Guard.notNull(treatmentStatusId, "treatmentStatusId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static TreatmentPlan register(
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId) {

        LocalDateTime now = LocalDateTime.now();
        TreatmentPlan aggregate = new TreatmentPlan(
                TreatmentPlanId.generate(),
                encounterId,
                professionalId,
                title,
                description,
                startDate,
                endDate,
                treatmentStatusId,
                now,
                now);

        aggregate.recordEvent(new TreatmentPlanRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static TreatmentPlan restore(
            TreatmentPlanId id,
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentPlan(
                id,
                encounterId,
                professionalId,
                title,
                description,
                startDate,
                endDate,
                treatmentStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId) {

        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.title = Guard.maxLength(Guard.notBlank(title, "title"), 200, "title");
        this.description = Guard.notBlank(description, "description");
        this.startDate = Guard.notNull(startDate, "startDate");
        this.endDate = Guard.notNull(endDate, "endDate");
        this.treatmentStatusId = Guard.notNull(treatmentStatusId, "treatmentStatusId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new TreatmentPlanUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new TreatmentPlanDeletedEvent(this.id, LocalDateTime.now()));
    }

    public TreatmentPlanId id() {
        return id;
    }

    public UUID encounterId() {
        return encounterId;
    }

    public UUID professionalId() {
        return professionalId;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public UUID treatmentStatusId() {
        return treatmentStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
