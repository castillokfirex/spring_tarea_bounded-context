CREATE TABLE chat_ai_run_metrics (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ai_run_id           UUID NOT NULL REFERENCES chat_ai_runs(id),
    prompt_tokens       INTEGER NOT NULL,
    completion_tokens   INTEGER NOT NULL,
    total_tokens        INTEGER NOT NULL,
    cost                DECIMAL(10,6) NOT NULL,
    created_at          TIMESTAMP NOT NULL DEFAULT now()
);

