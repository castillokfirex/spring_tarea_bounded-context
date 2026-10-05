CREATE TABLE chat_escalations (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id   UUID NOT NULL REFERENCES chat_conversations(id),
    status_id         UUID NOT NULL REFERENCES escalations_statuses(id),
    from_ai           BOOLEAN NOT NULL,
    reason            TEXT NOT NULL,
    created_at        TIMESTAMP NOT NULL DEFAULT now()
);

