package com.example.tarea.domain.providermodelai.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.providermodelai.event.ProviderModelAiDeletedEvent;
import com.example.tarea.domain.providermodelai.event.ProviderModelAiRegisteredEvent;
import com.example.tarea.domain.providermodelai.event.ProviderModelAiUpdatedEvent;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;

/**
 * Aggregate root del bounded context <b>providermodelai</b> (tabla <code>provider_models_ai</code>).
 */
public class ProviderModelAi extends AggregateRoot {

    private final ProviderModelAiId id;
    private String nameProviderAi;
    private String razonSocial;
    private String sitioWeb;
    private Boolean isActive;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProviderModelAi(
            ProviderModelAiId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            Boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameProviderAi = Guard.maxLength(Guard.notBlank(nameProviderAi, "nameProviderAi"), 100, "nameProviderAi");
        this.razonSocial = Guard.maxLength(razonSocial, 100, "razonSocial");
        this.sitioWeb = sitioWeb;
        this.isActive = Guard.notNull(isActive, "isActive");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ProviderModelAi register(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            Boolean isActive) {

        LocalDateTime now = LocalDateTime.now();
        ProviderModelAi aggregate = new ProviderModelAi(
                ProviderModelAiId.generate(),
                nameProviderAi,
                razonSocial,
                sitioWeb,
                isActive,
                now,
                now);

        aggregate.recordEvent(new ProviderModelAiRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ProviderModelAi restore(
            ProviderModelAiId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            Boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProviderModelAi(
                id,
                nameProviderAi,
                razonSocial,
                sitioWeb,
                isActive,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            Boolean isActive) {

        this.nameProviderAi = Guard.maxLength(Guard.notBlank(nameProviderAi, "nameProviderAi"), 100, "nameProviderAi");
        this.razonSocial = Guard.maxLength(razonSocial, 100, "razonSocial");
        this.sitioWeb = sitioWeb;
        this.isActive = Guard.notNull(isActive, "isActive");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ProviderModelAiUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ProviderModelAiDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ProviderModelAiId id() {
        return id;
    }

    public String nameProviderAi() {
        return nameProviderAi;
    }

    public String razonSocial() {
        return razonSocial;
    }

    public String sitioWeb() {
        return sitioWeb;
    }

    public Boolean isActive() {
        return isActive;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
