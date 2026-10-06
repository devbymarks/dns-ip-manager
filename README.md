# 🌐 DNS & IP Manager

Sistema de gerenciamento de redes, endereços IP, servidores, VLANs e registros DNS.

Projeto full-stack para praticar **SQL/PostgreSQL + Java + Spring Boot + Tomcat + Nginx + HTML/CSS/JavaScript**.

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

- Cadastro de redes/subnets
- Cadastro de VLANs
- Cadastro de servidores
- Controle de endereço IP
- Registro DNS
- Consulta de servidores por rede
- Dashboard com indicadores
- Validações de banco
- Índices PostgreSQL
- Views para consultas
- API REST
- Interface web
- Auditoria básica de alterações

## 🗂️ Estrutura

```text
dns-ip-manager/
├── database/
│   ├── 01-schema.sql
│   ├── 02-seed.sql
│   └── 03-views.sql
├── backend/
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/matheus/dnsipmanager/
│       │   ├── DnsIpManagerApplication.java
│       │   ├── config/CorsConfig.java
│       │   ├── controller/
│       │   ├── model/
│       │   ├── repository/
│       │   └── service/
│       └── resources/application.properties
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

## 🗄️ Banco de dados

Banco padrão:

```text
dns_ip_manager
```

Principais tabelas:

- `networks`
- `vlans`
- `servers`
- `ip_addresses`
- `dns_records`
- `audit_log`

O banco usa `PRIMARY KEY`, `FOREIGN KEY`, `UNIQUE`, `CHECK`, índices e views. O usuário administrativo de laboratório é criado pelo backend na primeira inicialização se ainda não existir.

## 🚀 Teste rápido com Docker

Requisitos:

- Docker
- Docker Compose

```bash
docker compose up -d postgres
```

Depois:

```bash
psql -h localhost -U dnsadmin -d dns_ip_manager
```

Senha padrão de laboratório: `dnsadmin`

> Para produção, altere a senha e nunca publique credenciais reais.

## ☕ Backend Java

Requisitos:

- Java 17+
- Maven 3.9+
- PostgreSQL

```bash
cd backend
mvn clean package
```

O resultado será:

```text
target/dns-ip-manager.war
```

Para executar localmente:

```bash
mvn spring-boot:run
```

API:

```text
http://localhost:8080/api
```

## 🐱 Tomcat

O projeto gera um WAR para implantação em Tomcat 10.1+.

Copie:

```bash
sudo cp backend/target/dns-ip-manager.war /opt/tomcat/webapps/
```

Após iniciar o Tomcat:

```text
http://localhost:8080/dns-ip-manager/
```

## 🌐 Nginx

Copie a configuração:

```bash
sudo cp nginx/dns-ip-manager.conf /etc/nginx/conf.d/
sudo nginx -t
sudo systemctl reload nginx
```

Acesse:

```text
http://localhost/
```

## 🔌 Principais endpoints

```text
GET    /api/dashboard
GET    /api/networks
POST   /api/networks
GET    /api/servers
POST   /api/servers
GET    /api/dns
POST   /api/dns
```

## 📚 Próximas evoluções

- Autenticação e login
- Controle de usuários/permissões
- Busca de IP disponível
- Importação de CSV
- Ping/status dos hosts
- Histórico completo
- Alertas
- LDAP/Active Directory
- Docker completo com PostgreSQL + backend + Nginx
- Testes automatizados
- HTTPS

## 👨‍💻 Autor

Matheus Marks

Projeto desenvolvido para estudos de SQL, PostgreSQL, Java, Linux, redes e infraestrutura.

⭐ Se este projeto foi útil para você, considere deixar uma estrela no repositório.


## 🔐 Evolução — autenticação e administração

A versão evoluída adiciona:

- Login com senha protegida por BCrypt
- Perfis `ADMIN`, `OPERATOR` e `VIEWER`
- Proteção das APIs no backend
- Sessão HTTP
- Administração de usuários
- Auditoria
- Verificação de servidores via backend
- Histórico de verificações
- Busca automática do próximo IP disponível
- Dashboard ampliado
- Telas de Redes, DNS, IPs, Usuários e Auditoria

### Primeiro acesso de laboratório

```text
Usuário: admin
Senha: admin123
Perfil: ADMIN
```

**Troque a senha antes de qualquer uso real.**

### API adicional

```text
POST /api/auth/login
POST /api/auth/logout
GET  /api/auth/me

GET  /api/users
POST /api/users

GET  /api/ip-addresses/available?networkId=1

POST /api/servers/{id}/ping
GET  /api/servers/checks/recent

GET  /api/audit
```

### Segurança

A autenticação é baseada em sessão e as senhas são armazenadas com BCrypt. As permissões são verificadas no backend. O endpoint de ping utiliza o IP previamente cadastrado no servidor; não aceita um comando arbitrário fornecido pelo usuário.

Para produção, recomenda-se trocar a senha inicial, usar HTTPS, armazenar credenciais por variáveis de ambiente/secret manager e revisar a política de sessão.
