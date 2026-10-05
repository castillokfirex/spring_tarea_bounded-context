CREATE TABLE phone_contacts ( -- esta tabla NO tiene created_at/updated_at en el diagrama
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    contact_id  UUID        NOT NULL REFERENCES contacts(id),
    phone       VARCHAR(30) NOT NULL,
    notes       TEXT
);

