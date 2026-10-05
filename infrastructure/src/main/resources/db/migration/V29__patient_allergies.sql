CREATE TABLE patient_allergies (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id    UUID         NOT NULL REFERENCES patients(id),
    substance     VARCHAR(200) NOT NULL,
    reaction      TEXT,
    severity      VARCHAR(20)  NOT NULL,
    active        BOOLEAN      NOT NULL,
    recorded_at   TIMESTAMP    NOT NULL DEFAULT now(),
    recorded_by   UUID         NOT NULL, -- (revisar) sin marca FK en el diagrama
    created_at    TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP    NOT NULL DEFAULT now()
);

