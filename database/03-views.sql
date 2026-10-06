CREATE OR REPLACE VIEW v_server_inventory AS
SELECT
    s.id,
    s.hostname,
    s.ip,
    s.operating_system,
    s.status,
    n.name AS network,
    n.cidr,
    v.vlan_number,
    v.name AS vlan
FROM servers s
JOIN networks n ON n.id = s.network_id
LEFT JOIN vlans v ON v.id = s.vlan_id;

CREATE OR REPLACE VIEW v_available_ips AS
SELECT
    i.id,
    i.ip,
    n.name AS network,
    n.cidr,
    i.description
FROM ip_addresses i
JOIN networks n ON n.id = i.network_id
WHERE i.status = 'DISPONIVEL';

CREATE OR REPLACE VIEW v_dns_inventory AS
SELECT
    id,
    hostname,
    domain,
    ip,
    record_type,
    ttl,
    created_at
FROM dns_records;
