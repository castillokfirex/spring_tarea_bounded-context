CREATE TABLE chat_ai_runs (
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id    UUID NOT NULL REFERENCES chat_conversations(id),
    message_id         UUID NOT NULL REFERENCES chat_messages(id),
    model_id           UUID NOT NULL REFERENCES ai_models(id),
    ai_run_status_id   UUID NOT NULL REFERENCES ai_runs_statuses(id),
    created_at         TIMESTAMP NOT NULL DEFAULT now(),
    updated_at         TIMESTAMP NOT NULL DEFAULT now()
);

