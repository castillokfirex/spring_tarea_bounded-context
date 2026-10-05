CREATE TABLE genders (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(50) NOT NULL UNIQUE,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP   NOT NULL DEFAULT now()
);

