CREATE TABLE chat_participants (
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id       UUID NOT NULL REFERENCES chat_conversations(id),
    participant_type_id   UUID NOT NULL REFERENCES sender_types(id),
    patient_id            UUID REFERENCES patients(id),
    professional_id       UUID REFERENCES professionals(id),
    created_at            TIMESTAMP NOT NULL DEFAULT now(),
    updated_at            TIMESTAMP NOT NULL DEFAULT now()
);

