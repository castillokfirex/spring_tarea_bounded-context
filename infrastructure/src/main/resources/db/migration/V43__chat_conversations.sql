-- =====================================================================
-- 6. MÓDULO DE CHAT / MENSAJERÍA / IA
-- =====================================================================

CREATE TABLE chat_conversations (
    id                       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_status_id   UUID      NOT NULL REFERENCES conversations_statuses(id),
    priority_id              UUID      NOT NULL REFERENCES priorities(id),
    last_message_at          TIMESTAMP,
    closed                   BOOLEAN,
    closed_at                TIMESTAMP,
    closed_by                UUID, -- (revisar) sin marca FK en el diagrama
    created_at               TIMESTAMP NOT NULL DEFAULT now(),
    updated_at               TIMESTAMP NOT NULL DEFAULT now()
);

