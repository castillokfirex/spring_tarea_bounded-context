-- --- Catálogos del módulo de chat / IA (usan "statuses", una sola s) --

CREATE TABLE priorities (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name_priority VARCHAR(50) NOT NULL,
    created_at    TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP   NOT NULL DEFAULT now()
);

