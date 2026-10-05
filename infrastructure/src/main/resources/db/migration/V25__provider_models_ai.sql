CREATE TABLE provider_models_ai (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name_provider_ai  VARCHAR(100) NOT NULL,
    razon_social      VARCHAR(100),
    sitio_web         TEXT,
    "isActive"        BOOLEAN      NOT NULL, -- camelCase tal como está en el diagrama
    created_at        TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at        TIMESTAMP    NOT NULL DEFAULT now()
);

