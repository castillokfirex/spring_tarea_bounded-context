package com.example.tarea.domain.relationshiptype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.relationshiptype.event.RelationshipTypeDeletedEvent;
import com.example.tarea.domain.relationshiptype.event.RelationshipTypeRegisteredEvent;
import com.example.tarea.domain.relationshiptype.event.RelationshipTypeUpdatedEvent;
import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;

/**
 * Aggregate root del bounded context <b>relationshiptype</b> (tabla <code>relationship_types</code>).
 */
public class RelationshipType extends AggregateRoot {

    private final RelationshipTypeId id;
    private String description;

    private RelationshipType(
            RelationshipTypeId id,
            String description) {
        this.id = Guard.notNull(id, "id");
        this.description = Guard.maxLength(Guard.notBlank(description, "description"), 50, "description");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static RelationshipType register(
            String description) {

        LocalDateTime now = LocalDateTime.now();
        RelationshipType aggregate = new RelationshipType(
                RelationshipTypeId.generate(),
                description);

        aggregate.recordEvent(new RelationshipTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static RelationshipType restore(
            RelationshipTypeId id,
            String description) {
        return new RelationshipType(
                id,
                description);
    }

    public void update(
            String description) {

        this.description = Guard.maxLength(Guard.notBlank(description, "description"), 50, "description");

        recordEvent(new RelationshipTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new RelationshipTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public RelationshipTypeId id() {
        return id;
    }

    public String description() {
        return description;
    }
}
