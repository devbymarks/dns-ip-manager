INSERT INTO networks (name, cidr, gateway, description)
VALUES
('REDE-SERVIDORES', '192.168.10.0/24', '192.168.10.1', 'Rede de servidores'),
('REDE-USUARIOS', '192.168.20.0/24', '192.168.20.1', 'Rede de usuários')
ON CONFLICT DO NOTHING;

INSERT INTO vlans (vlan_number, name, description)
VALUES
(10, 'SERVIDORES', 'VLAN dos servidores'),
(20, 'USUARIOS', 'VLAN dos usuários')
ON CONFLICT DO NOTHING;

INSERT INTO servers (hostname, ip, operating_system, status, network_id, vlan_id, description)
SELECT 'srv-linux-01', '192.168.10.10', 'Ubuntu Linux', 'ATIVO',
       (SELECT id FROM networks WHERE name='REDE-SERVIDORES'),
       (SELECT id FROM vlans WHERE name='SERVIDORES'),
       'Servidor Linux de laboratório'
WHERE NOT EXISTS (SELECT 1 FROM servers WHERE hostname='srv-linux-01');

INSERT INTO servers (hostname, ip, operating_system, status, network_id, vlan_id, description)
SELECT 'srv-postgres-01', '192.168.10.20', 'Ubuntu Linux', 'ATIVO',
       (SELECT id FROM networks WHERE name='REDE-SERVIDORES'),
       (SELECT id FROM vlans WHERE name='SERVIDORES'),
       'Servidor PostgreSQL de laboratório'
WHERE NOT EXISTS (SELECT 1 FROM servers WHERE hostname='srv-postgres-01');

INSERT INTO dns_records (hostname, domain, ip, record_type)
VALUES
('srv-linux-01', 'lab.local', '192.168.10.10', 'A'),
('srv-postgres-01', 'lab.local', '192.168.10.20', 'A')
ON CONFLICT DO NOTHING;
