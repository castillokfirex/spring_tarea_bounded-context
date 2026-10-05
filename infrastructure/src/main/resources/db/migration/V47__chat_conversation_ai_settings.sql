CREATE TABLE chat_conversation_ai_settings (
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id    UUID    NOT NULL REFERENCES chat_conversations(id),
    ai_enabled         BOOLEAN NOT NULL,
    default_model_id   UUID    NOT NULL REFERENCES ai_models(id),
    created_at         TIMESTAMP NOT NULL DEFAULT now(),
    updated_at         TIMESTAMP NOT NULL DEFAULT now()
);

