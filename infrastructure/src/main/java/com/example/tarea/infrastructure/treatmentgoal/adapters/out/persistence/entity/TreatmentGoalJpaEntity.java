package com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "treatment_goals")
public class TreatmentGoalJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "treatment_plan_id", nullable = false)
    private UUID treatmentPlanId;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "target_date", nullable = false)
    private LocalDate targetDate;

    @Column(name = "completed_at", nullable = true)
    private LocalDateTime completedAt;

    @Column(name = "notes", nullable = false, columnDefinition = "text")
    private String notes;

    @Column(name = "treatment_goal_id", nullable = false)
    private UUID treatmentGoalId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public TreatmentGoalJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTreatmentPlanId() {
        return treatmentPlanId;
    }

    public void setTreatmentPlanId(UUID treatmentPlanId) {
        this.treatmentPlanId = treatmentPlanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UUID getTreatmentGoalId() {
        return treatmentGoalId;
    }

    public void setTreatmentGoalId(UUID treatmentGoalId) {
        this.treatmentGoalId = treatmentGoalId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
