
CREATE TABLE IF NOT EXISTS server_checks (
    id BIGSERIAL PRIMARY KEY,
    server_id BIGINT NOT NULL REFERENCES servers(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL CHECK (status IN ('ONLINE','OFFLINE','ERROR')),
    response_time BIGINT,
    checked_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    message TEXT
);

CREATE INDEX IF NOT EXISTS idx_server_checks_server_time
ON server_checks(server_id, checked_at DESC);

CREATE OR REPLACE VIEW v_latest_server_checks AS
SELECT DISTINCT ON (server_id)
    server_id, status, response_time, checked_at, message
FROM server_checks
ORDER BY server_id, checked_at DESC;
