
CREATE TABLE IF NOT EXISTS audit_log (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(80),
    action VARCHAR(30) NOT NULL,
    entity_name VARCHAR(80) NOT NULL,
    entity_id BIGINT,
    details JSONB,
    source_ip INET,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_audit_user ON audit_log(username);
CREATE INDEX IF NOT EXISTS idx_audit_action ON audit_log(action);
CREATE INDEX IF NOT EXISTS idx_audit_entity ON audit_log(entity_name);
CREATE INDEX IF NOT EXISTS idx_audit_created ON audit_log(created_at DESC);
