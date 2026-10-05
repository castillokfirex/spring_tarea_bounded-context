CREATE TABLE risk_levels (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code        VARCHAR(20) NOT NULL UNIQUE,
    name        VARCHAR(50) NOT NULL,
    active      BOOLEAN     NOT NULL,
    severity    INTEGER,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP   NOT NULL DEFAULT now()
);

