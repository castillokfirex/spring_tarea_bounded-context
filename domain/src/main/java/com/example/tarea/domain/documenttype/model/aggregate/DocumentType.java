package com.example.tarea.domain.documenttype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.documenttype.event.DocumentTypeDeletedEvent;
import com.example.tarea.domain.documenttype.event.DocumentTypeRegisteredEvent;
import com.example.tarea.domain.documenttype.event.DocumentTypeUpdatedEvent;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;

/**
 * Aggregate root del bounded context <b>documenttype</b> (tabla <code>document_types</code>).
 */
public class DocumentType extends AggregateRoot {

    private final DocumentTypeId id;
    private String code;
    private String name;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DocumentType(
            DocumentTypeId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static DocumentType register(
            String code,
            String name,
            Boolean active) {

        LocalDateTime now = LocalDateTime.now();
        DocumentType aggregate = new DocumentType(
                DocumentTypeId.generate(),
                code,
                name,
                active,
                now,
                now);

        aggregate.recordEvent(new DocumentTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static DocumentType restore(
            DocumentTypeId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new DocumentType(
                id,
                code,
                name,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            Boolean active) {

        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new DocumentTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new DocumentTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public DocumentTypeId id() {
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

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
