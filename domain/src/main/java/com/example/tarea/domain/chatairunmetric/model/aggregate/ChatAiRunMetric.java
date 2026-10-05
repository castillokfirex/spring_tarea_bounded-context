package com.example.tarea.domain.chatairunmetric.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatairunmetric.event.ChatAiRunMetricDeletedEvent;
import com.example.tarea.domain.chatairunmetric.event.ChatAiRunMetricRegisteredEvent;
import com.example.tarea.domain.chatairunmetric.event.ChatAiRunMetricUpdatedEvent;
import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatairunmetric</b> (tabla <code>chat_ai_run_metrics</code>).
 */
public class ChatAiRunMetric extends AggregateRoot {

    private final ChatAiRunMetricId id;
    private UUID aiRunId;
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
    private BigDecimal cost;
    private final LocalDateTime createdAt;

    private ChatAiRunMetric(
            ChatAiRunMetricId id,
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost,
            LocalDateTime createdAt) {
        this.id = Guard.notNull(id, "id");
        this.aiRunId = Guard.notNull(aiRunId, "aiRunId");
        this.promptTokens = Guard.notNull(promptTokens, "promptTokens");
        this.completionTokens = Guard.notNull(completionTokens, "completionTokens");
        this.totalTokens = Guard.notNull(totalTokens, "totalTokens");
        this.cost = Guard.notNull(cost, "cost");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatAiRunMetric register(
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost) {

        LocalDateTime now = LocalDateTime.now();
        ChatAiRunMetric aggregate = new ChatAiRunMetric(
                ChatAiRunMetricId.generate(),
                aiRunId,
                promptTokens,
                completionTokens,
                totalTokens,
                cost,
                now);

        aggregate.recordEvent(new ChatAiRunMetricRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatAiRunMetric restore(
            ChatAiRunMetricId id,
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost,
            LocalDateTime createdAt) {
        return new ChatAiRunMetric(
                id,
                aiRunId,
                promptTokens,
                completionTokens,
                totalTokens,
                cost,
                createdAt);
    }

    public void update(
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost) {

        this.aiRunId = Guard.notNull(aiRunId, "aiRunId");
        this.promptTokens = Guard.notNull(promptTokens, "promptTokens");
        this.completionTokens = Guard.notNull(completionTokens, "completionTokens");
        this.totalTokens = Guard.notNull(totalTokens, "totalTokens");
        this.cost = Guard.notNull(cost, "cost");

        recordEvent(new ChatAiRunMetricUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatAiRunMetricDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatAiRunMetricId id() {
        return id;
    }

    public UUID aiRunId() {
        return aiRunId;
    }

    public Integer promptTokens() {
        return promptTokens;
    }

    public Integer completionTokens() {
        return completionTokens;
    }

    public Integer totalTokens() {
        return totalTokens;
    }

    public BigDecimal cost() {
        return cost;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
