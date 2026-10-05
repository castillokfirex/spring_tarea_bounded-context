package com.example.tarea.domain.assessmenttype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.assessmenttype.event.AssessmentTypeDeletedEvent;
import com.example.tarea.domain.assessmenttype.event.AssessmentTypeRegisteredEvent;
import com.example.tarea.domain.assessmenttype.event.AssessmentTypeUpdatedEvent;
import com.example.tarea.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>assessmenttype</b> (tabla <code>assessment_types</code>).
 */
public class AssessmentType extends AggregateRoot {

    private final AssessmentTypeId id;
    private String code;
    private String name;
    private Boolean active;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AssessmentType(
            AssessmentTypeId id,
            String code,
            String name,
            Boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.description = description;
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static AssessmentType register(
            String code,
            String name,
            Boolean active,
            String description) {

        LocalDateTime now = LocalDateTime.now();
        AssessmentType aggregate = new AssessmentType(
                AssessmentTypeId.generate(),
                code,
                name,
                active,
                description,
                now,
                now);

        aggregate.recordEvent(new AssessmentTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static AssessmentType restore(
            AssessmentTypeId id,
            String code,
            String name,
            Boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AssessmentType(
                id,
                code,
                name,
                active,
                description,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            Boolean active,
            String description) {

        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.description = description;
        this.updatedAt = LocalDateTime.now();

        recordEvent(new AssessmentTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new AssessmentTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public AssessmentTypeId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public Boolean active() {
        return active;
    }

    public String description() {
        return description;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
