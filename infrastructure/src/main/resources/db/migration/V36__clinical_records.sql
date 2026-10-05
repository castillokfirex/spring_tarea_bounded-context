-- =====================================================================
-- 5. MÓDULO CLÍNICO
-- =====================================================================

CREATE TABLE clinical_records (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id      UUID        NOT NULL REFERENCES patients(id),
    creation_date   TIMESTAMP   NOT NULL DEFAULT now(),
    record_number   VARCHAR(50) NOT NULL UNIQUE,
    opened_at       TIMESTAMP   NOT NULL DEFAULT now(),
    closed_at       TIMESTAMP,
    status_id       UUID        NOT NULL REFERENCES clinical_record_statusses(id),
    created_at      TIMESTAMP   NOT NULL DEFAULT now(),
    created_by      UUID        NOT NULL REFERENCES professionals(id)
);

