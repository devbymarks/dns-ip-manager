CREATE TABLE IF NOT EXISTS networks (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    cidr CIDR NOT NULL UNIQUE,
    gateway INET,
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS vlans (
    id BIGSERIAL PRIMARY KEY,
    vlan_number INTEGER NOT NULL UNIQUE CHECK (vlan_number BETWEEN 1 AND 4094),
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS servers (
    id BIGSERIAL PRIMARY KEY,
    hostname VARCHAR(120) NOT NULL UNIQUE,
    ip INET NOT NULL UNIQUE,
    operating_system VARCHAR(100),
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVO'
        CHECK (status IN ('ATIVO','INATIVO','MANUTENCAO')),
    network_id BIGINT NOT NULL REFERENCES networks(id),
    vlan_id BIGINT REFERENCES vlans(id),
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ip_addresses (
    id BIGSERIAL PRIMARY KEY,
    ip INET NOT NULL UNIQUE,
    network_id BIGINT NOT NULL REFERENCES networks(id),
    hostname VARCHAR(120),
    status VARCHAR(20) NOT NULL DEFAULT 'DISPONIVEL'
        CHECK (status IN ('DISPONIVEL','OCUPADO','RESERVADO')),
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS dns_records (
    id BIGSERIAL PRIMARY KEY,
    hostname VARCHAR(120) NOT NULL,
    domain VARCHAR(255) NOT NULL,
    ip INET NOT NULL,
    record_type VARCHAR(10) NOT NULL DEFAULT 'A'
        CHECK (record_type IN ('A','AAAA','CNAME')),
    ttl INTEGER NOT NULL DEFAULT 300 CHECK (ttl > 0),
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(hostname, domain, record_type)
);

CREATE INDEX IF NOT EXISTS idx_servers_network ON servers(network_id);
CREATE INDEX IF NOT EXISTS idx_servers_status ON servers(status);
CREATE INDEX IF NOT EXISTS idx_dns_ip ON dns_records(ip);
CREATE INDEX IF NOT EXISTS idx_ip_network_status ON ip_addresses(network_id, status);
CREATE INDEX IF NOT EXISTS idx_audit_created_at ON audit_log(created_at DESC);
