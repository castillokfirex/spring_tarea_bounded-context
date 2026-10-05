package com.example.tarea.domain.diagnosticsystem.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.diagnosticsystem.event.DiagnosticSystemDeletedEvent;
import com.example.tarea.domain.diagnosticsystem.event.DiagnosticSystemRegisteredEvent;
import com.example.tarea.domain.diagnosticsystem.event.DiagnosticSystemUpdatedEvent;
import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

/**
 * Aggregate root del bounded context <b>diagnosticsystem</b> (tabla <code>diagnostic_systems</code>).
 */
public class DiagnosticSystem extends AggregateRoot {

    private final DiagnosticSystemId id;
    private String code;
    private String name;
    private Boolean active;
    private String version;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DiagnosticSystem(
            DiagnosticSystemId id,
            String code,
            String name,
            Boolean active,
            String version,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.version = Guard.maxLength(version, 20, "version");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static DiagnosticSystem register(
            String code,
            String name,
            Boolean active,
            String version) {

        LocalDateTime now = LocalDateTime.now();
        DiagnosticSystem aggregate = new DiagnosticSystem(
                DiagnosticSystemId.generate(),
                code,
                name,
                active,
                version,
                now,
                now);

        aggregate.recordEvent(new DiagnosticSystemRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static DiagnosticSystem restore(
            DiagnosticSystemId id,
            String code,
            String name,
            Boolean active,
            String version,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new DiagnosticSystem(
                id,
                code,
                name,
                active,
                version,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            Boolean active,
            String version) {

        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.version = Guard.maxLength(version, 20, "version");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new DiagnosticSystemUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new DiagnosticSystemDeletedEvent(this.id, LocalDateTime.now()));
    }

    public DiagnosticSystemId id() {
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

    public String version() {
        return version;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
