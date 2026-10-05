CREATE TABLE sender_types (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name_type   VARCHAR(50) NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP   NOT NULL DEFAULT now()
);

