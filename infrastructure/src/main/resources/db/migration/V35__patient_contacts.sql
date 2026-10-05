CREATE TABLE patient_contacts ( -- esta tabla NO tiene created_at/updated_at en el diagrama
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    contact_id            UUID    NOT NULL REFERENCES contacts(id),
    patient_id            UUID    NOT NULL REFERENCES patients(id),
    is_primary_contact    BOOLEAN NOT NULL,
    is_emergency_contact  BOOLEAN NOT NULL,
    relationship_type_id  UUID    NOT NULL REFERENCES relationship_types(id)
);

