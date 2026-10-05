CREATE TABLE email_contacts (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    contact_id  UUID         NOT NULL REFERENCES contacts(id),
    email       VARCHAR(150) NOT NULL UNIQUE,
    notes       TEXT         NOT NULL,
    created_at  TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT now()
);

