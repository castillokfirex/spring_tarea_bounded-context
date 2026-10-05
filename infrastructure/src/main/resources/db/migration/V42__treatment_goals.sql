CREATE TABLE treatment_goals (
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    treatment_plan_id  UUID NOT NULL REFERENCES treatment_plans(id),
    description        TEXT NOT NULL,
    target_date        DATE NOT NULL,
    completed_at       TIMESTAMP,
    notes              TEXT NOT NULL,
    treatment_goal_id  UUID NOT NULL REFERENCES treatment_goal_statusses(id), -- (revisar) nombre ambiguo, ver nota arriba
    created_at         TIMESTAMP NOT NULL DEFAULT now(),
    updated_at         TIMESTAMP NOT NULL DEFAULT now()
);

