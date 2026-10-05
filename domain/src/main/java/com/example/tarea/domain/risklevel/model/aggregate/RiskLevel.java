package com.example.tarea.domain.risklevel.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.risklevel.event.RiskLevelDeletedEvent;
import com.example.tarea.domain.risklevel.event.RiskLevelRegisteredEvent;
import com.example.tarea.domain.risklevel.event.RiskLevelUpdatedEvent;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;

/**
 * Aggregate root del bounded context <b>risklevel</b> (tabla <code>risk_levels</code>).
 */
public class RiskLevel extends AggregateRoot {

    private final RiskLevelId id;
    private String code;
    private String name;
    private Boolean active;
    private Integer severity;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private RiskLevel(
            RiskLevelId id,
            String code,
            String name,
            Boolean active,
            Integer severity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.severity = severity;
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static RiskLevel register(
            String code,
            String name,
            Boolean active,
            Integer severity) {

        LocalDateTime now = LocalDateTime.now();
        RiskLevel aggregate = new RiskLevel(
                RiskLevelId.generate(),
                code,
                name,
                active,
                severity,
                now,
                now);

        aggregate.recordEvent(new RiskLevelRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static RiskLevel restore(
            RiskLevelId id,
            String code,
            String name,
            Boolean active,
            Integer severity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new RiskLevel(
                id,
                code,
                name,
                active,
                severity,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            Boolean active,
            Integer severity) {

        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.severity = severity;
        this.updatedAt = LocalDateTime.now();

        recordEvent(new RiskLevelUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new RiskLevelDeletedEvent(this.id, LocalDateTime.now()));
    }

    public RiskLevelId id() {
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

    public Integer severity() {
        return severity;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
