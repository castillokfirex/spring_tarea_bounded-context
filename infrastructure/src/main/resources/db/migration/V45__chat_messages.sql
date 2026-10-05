CREATE TABLE chat_messages (
    id               UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id  UUID NOT NULL REFERENCES chat_conversations(id),
    message_type_id  UUID NOT NULL REFERENCES message_types(id),
    participant_id   UUID NOT NULL REFERENCES chat_participants(id),
    content          JSONB NOT NULL,
    metadata         JSONB NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT now()
);

