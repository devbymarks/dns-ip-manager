# 🌐 DNS & IP Manager

Sistema web para gerenciamento de **redes, endereços IP, VLANs, servidores e registros DNS**, desenvolvido com **Java, Spring Boot, PostgreSQL, Tomcat e Nginx**.

Projeto desenvolvido como laboratório prático de **SQL, PostgreSQL, desenvolvimento backend, infraestrutura Linux, redes e administração de servidores**.

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- PostgreSQL
- Apache Tomcat 10.1+
- Nginx
- HTML5, CSS3 e JavaScript
- BCrypt
- REST API
- Docker / Docker Compose
- Linux
- Maven

## 🏗️ Arquitetura

```text
Navegador
   │
   ▼
Nginx :80
   │
   ▼
Tomcat :8080
   │
   ▼
Java / Spring Boot WAR
   │
   ▼
PostgreSQL :5432
```

## ✨ Funcionalidades

### 🌐 Redes
- Cadastro de redes e subnets
- Máscara de rede
- Gateway
- VLAN associada
- Consulta de redes
- Relacionamento entre redes e servidores

### 🖥️ Servidores
- Cadastro de servidores
- Endereço IP
- Nome do servidor
- Rede associada
- Status
- Verificação de conectividade
- Histórico de verificações

### 🔢 Endereços IP
- Cadastro de IPs
- Controle de IPs ocupados
- Controle de IPs reservados
- Identificação de IPs disponíveis
- Busca automática do próximo IP disponível
- Exclusão de endereços de rede e broadcast da disponibilidade

### 🧩 VLANs
- Cadastro de VLANs
- Identificação e nome
- Relacionamento com redes

### 🌎 DNS
- Cadastro de registros DNS
- Nome do host
- Tipo de registro
- Valor
- TTL
- Relacionamento com servidores

### 📊 Dashboard
Indicadores de:
- Redes cadastradas
- Servidores
- Endereços IP
- Registros DNS
- Verificações recentes

### 🔐 Autenticação
- Login e logout
- Sessão HTTP
- Senhas protegidas com BCrypt
- Controle de acesso no backend

Perfis:

| Perfil | Permissões |
|---|---|
| ADMIN | Acesso completo |
| OPERATOR | Operações de gerenciamento |
| VIEWER | Consulta |

### 👥 Usuários
- Criação de usuários
- Definição de perfil
- Consulta de usuários
- Gerenciamento de acesso

### 📋 Auditoria
Registro de operações importantes, como:
- Login
- Logout
- Criação de registros
- Alterações
- Operações administrativas
- Verificações de servidores

### 📡 Monitoramento
Verificação de conectividade de servidores cadastrados e armazenamento do histórico no PostgreSQL.

## 🗄️ Banco de Dados

Banco:

```text
PostgreSQL
```

Nome padrão:

```text
dns_ip_manager
```

Principais tabelas:

```text
networks
vlans
servers
ip_addresses
dns_records
roles
users
server_checks
audit_log
```

Recursos utilizados:

- PRIMARY KEY
- FOREIGN KEY
- UNIQUE
- CHECK
- INDEX
- VIEW
- Constraints
- Relacionamentos

## 📁 Estrutura

```text
dns-ip-manager/
├── database/
│   ├── 01-schema.sql
│   ├── 02-seed.sql
│   ├── 03-views.sql
│   ├── 04-auth.sql
│   ├── 05-monitoring.sql
│   └── 06-audit.sql
├── backend/
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/matheus/dnsipmanager/
│       │   ├── DnsIpManagerApplication.java
│       │   ├── config/
│       │   ├── controller/
│       │   ├── model/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
│           └── application.properties
├── frontend/
│   ├── index.html
│   ├── css/style.css
│   └── js/app.js
├── nginx/
│   └── dns-ip-manager.conf
├── tomcat/
│   └── README.md
├── docs/
│   └── ARCHITECTURE.md
├── docker-compose.yml
└── LICENSE
```

## 🐳 PostgreSQL com Docker

### Requisitos

- Docker
- Docker Compose

Inicie o PostgreSQL:

```bash
docker compose up -d postgres
```

Conecte ao banco:

```bash
psql -h localhost -U dnsadmin -d dns_ip_manager
```

> Para produção, altere as credenciais e nunca publique senhas reais.

## ☕ Backend Java

### Requisitos

- Java 17+
- Maven 3.9+
- PostgreSQL

```bash
cd backend
mvn clean package
```

O WAR será gerado em:

```text
target/dns-ip-manager.war
```

Para executar localmente:

```bash
mvn spring-boot:run
```

## 🐱 Deploy no Tomcat

O projeto gera um WAR compatível com Apache Tomcat 10.1+.

```bash
sudo cp target/dns-ip-manager.war /opt/tomcat/webapps/
```

Acesso:

```text
http://localhost:8080/dns-ip-manager/
```

## 🌐 Nginx

Configuração:

```text
nginx/dns-ip-manager.conf
```

Instalação:

```bash
sudo cp nginx/dns-ip-manager.conf /etc/nginx/conf.d/
sudo nginx -t
sudo systemctl reload nginx
```

Acesso:

```text
http://localhost/
```

## 🔌 REST API

### Autenticação

```http
POST /api/auth/login
POST /api/auth/logout
GET  /api/auth/me
```

### Dashboard

```http
GET /api/dashboard
```

### Redes

```http
GET  /api/networks
POST /api/networks
```

### Servidores

```http
GET  /api/servers
POST /api/servers
POST /api/servers/{id}/ping
GET  /api/servers/checks/recent
```

### DNS

```http
GET  /api/dns
POST /api/dns
```

### IPs disponíveis

```http
GET /api/ip-addresses/available?networkId=1
```

### Usuários

```http
GET  /api/users
POST /api/users
```

### Auditoria

```http
GET /api/audit
```

## 🔐 Primeiro acesso

Usuário administrativo de laboratório:

```text
Usuário: admin
Senha: admin123
Perfil: ADMIN
```

> ⚠️ Credenciais destinadas somente ao laboratório. Altere a senha antes de qualquer uso real.

## 🛡️ Segurança

O projeto utiliza:

- Autenticação baseada em sessão
- BCrypt para senhas
- Controle de permissões por perfil
- Proteção das APIs no backend
- Registro de auditoria
- Validações de dados
- Verificação de acesso aos recursos
- Ping utilizando o IP previamente cadastrado

O endpoint de ping não permite comandos arbitrários enviados pelo usuário.

## 📚 Scripts SQL

```text
01-schema.sql       → Estrutura principal
02-seed.sql         → Dados iniciais
03-views.sql        → Views e consultas
04-auth.sql         → Autenticação
05-monitoring.sql   → Monitoramento
06-audit.sql        → Auditoria
```

## 🎯 Objetivo

O projeto coloca em prática conhecimentos de:

- SQL e PostgreSQL
- Java e Spring Boot
- REST API
- Linux
- Tomcat
- Nginx
- Redes
- DNS
- VLAN
- Administração de servidores
- Segurança
- Docker
- Git e GitHub

O cenário foi pensado para se aproximar de ambientes reais de **infraestrutura e administração de servidores**.

## 🔮 Próximas Evoluções

- Importação de redes e IPs via CSV
- Sistema de alertas
- Notificações por e-mail
- HTTPS
- LDAP / Active Directory
- Dashboard avançado
- Containerização completa
- Testes automatizados
- Histórico avançado de disponibilidade
- Backup e restauração do banco
- Permissões mais detalhadas
- Melhorias de responsividade

## 👨‍💻 Autor

**Matheus Marks**

Projeto desenvolvido para estudos e prática de **SQL, PostgreSQL, Java, Linux, redes e infraestrutura de servidores**.

---

⭐ **Se este projeto foi útil para você, considere deixar uma estrela no repositório.**
