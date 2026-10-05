package com.example.tarea.domain.treatmentgoal.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.treatmentgoal.event.TreatmentGoalDeletedEvent;
import com.example.tarea.domain.treatmentgoal.event.TreatmentGoalRegisteredEvent;
import com.example.tarea.domain.treatmentgoal.event.TreatmentGoalUpdatedEvent;
import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

/**
 * Aggregate root del bounded context <b>treatmentgoal</b> (tabla <code>treatment_goals</code>).
 */
public class TreatmentGoal extends AggregateRoot {

    private final TreatmentGoalId id;
    private UUID treatmentPlanId;
    private String description;
    private LocalDate targetDate;
    private LocalDateTime completedAt;
    private String notes;
    private UUID treatmentGoalId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentGoal(
            TreatmentGoalId id,
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.treatmentPlanId = Guard.notNull(treatmentPlanId, "treatmentPlanId");
        this.description = Guard.notBlank(description, "description");
        this.targetDate = Guard.notNull(targetDate, "targetDate");
        this.completedAt = completedAt;
        this.notes = Guard.notBlank(notes, "notes");
        this.treatmentGoalId = Guard.notNull(treatmentGoalId, "treatmentGoalId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static TreatmentGoal register(
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId) {

        LocalDateTime now = LocalDateTime.now();
        TreatmentGoal aggregate = new TreatmentGoal(
                TreatmentGoalId.generate(),
                treatmentPlanId,
                description,
                targetDate,
                completedAt,
                notes,
                treatmentGoalId,
                now,
                now);

        aggregate.recordEvent(new TreatmentGoalRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static TreatmentGoal restore(
            TreatmentGoalId id,
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentGoal(
                id,
                treatmentPlanId,
                description,
                targetDate,
                completedAt,
                notes,
                treatmentGoalId,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId) {

        this.treatmentPlanId = Guard.notNull(treatmentPlanId, "treatmentPlanId");
        this.description = Guard.notBlank(description, "description");
        this.targetDate = Guard.notNull(targetDate, "targetDate");
        this.completedAt = completedAt;
        this.notes = Guard.notBlank(notes, "notes");
        this.treatmentGoalId = Guard.notNull(treatmentGoalId, "treatmentGoalId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new TreatmentGoalUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new TreatmentGoalDeletedEvent(this.id, LocalDateTime.now()));
    }

    public TreatmentGoalId id() {
        return id;
    }

    public UUID treatmentPlanId() {
        return treatmentPlanId;
    }

    public String description() {
        return description;
    }

    public LocalDate targetDate() {
        return targetDate;
    }

    public LocalDateTime completedAt() {
        return completedAt;
    }

    public String notes() {
        return notes;
    }

    public UUID treatmentGoalId() {
        return treatmentGoalId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
