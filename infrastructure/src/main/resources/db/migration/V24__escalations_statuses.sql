CREATE TABLE escalations_statuses (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name_status VARCHAR(50) NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP   NOT NULL DEFAULT now()
);

