package com.example.tarea.domain.aimodel.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.tarea.domain.aimodel.event.AiModelDeletedEvent;
import com.example.tarea.domain.aimodel.event.AiModelRegisteredEvent;
import com.example.tarea.domain.aimodel.event.AiModelUpdatedEvent;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>aimodel</b> (tabla <code>ai_models</code>).
 */
public class AiModel extends AggregateRoot {

    private final AiModelId id;
    private String providerModelId;
    private String nameModel;
    private String modelKey;
    private BigDecimal inputTokenPrice;
    private BigDecimal outputTokenPrice;
    private Integer maxTokens;
    private Integer contextWindow;
    private Boolean isActive;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AiModel(
            AiModelId id,
            String providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            Boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.providerModelId = Guard.maxLength(Guard.notBlank(providerModelId, "providerModelId"), 50, "providerModelId");
        this.nameModel = Guard.maxLength(Guard.notBlank(nameModel, "nameModel"), 100, "nameModel");
        this.modelKey = Guard.maxLength(Guard.notBlank(modelKey, "modelKey"), 170, "modelKey");
        this.inputTokenPrice = Guard.notNull(inputTokenPrice, "inputTokenPrice");
        this.outputTokenPrice = Guard.notNull(outputTokenPrice, "outputTokenPrice");
        this.maxTokens = Guard.notNull(maxTokens, "maxTokens");
        this.contextWindow = Guard.notNull(contextWindow, "contextWindow");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static AiModel register(
            String providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            Boolean isActive) {

        LocalDateTime now = LocalDateTime.now();
        AiModel aggregate = new AiModel(
                AiModelId.generate(),
                providerModelId,
                nameModel,
                modelKey,
                inputTokenPrice,
                outputTokenPrice,
                maxTokens,
                contextWindow,
                isActive,
                now,
                now);

        aggregate.recordEvent(new AiModelRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static AiModel restore(
            AiModelId id,
            String providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            Boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AiModel(
                id,
                providerModelId,
                nameModel,
                modelKey,
                inputTokenPrice,
                outputTokenPrice,
                maxTokens,
                contextWindow,
                isActive,
                createdAt,
                updatedAt);
    }

    public void update(
            String providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            Boolean isActive) {

        this.providerModelId = Guard.maxLength(Guard.notBlank(providerModelId, "providerModelId"), 50, "providerModelId");
        this.nameModel = Guard.maxLength(Guard.notBlank(nameModel, "nameModel"), 100, "nameModel");
        this.modelKey = Guard.maxLength(Guard.notBlank(modelKey, "modelKey"), 170, "modelKey");
        this.inputTokenPrice = Guard.notNull(inputTokenPrice, "inputTokenPrice");
        this.outputTokenPrice = Guard.notNull(outputTokenPrice, "outputTokenPrice");
        this.maxTokens = Guard.notNull(maxTokens, "maxTokens");
        this.contextWindow = Guard.notNull(contextWindow, "contextWindow");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new AiModelUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new AiModelDeletedEvent(this.id, LocalDateTime.now()));
    }

    public AiModelId id() {
        return id;
    }

    public String providerModelId() {
        return providerModelId;
    }

    public String nameModel() {
        return nameModel;
    }

    public String modelKey() {
        return modelKey;
    }

    public BigDecimal inputTokenPrice() {
        return inputTokenPrice;
    }

    public BigDecimal outputTokenPrice() {
        return outputTokenPrice;
    }

    public Integer maxTokens() {
        return maxTokens;
    }

    public Integer contextWindow() {
        return contextWindow;
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
