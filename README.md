# 🌐 DNS & IP Manager

Sistema web para gerenciamento de **redes, endereços IP, VLANs, servidores e registros DNS**, desenvolvido com **Java, Spring Boot, PostgreSQL, Tomcat e Nginx**.

Projeto idealizado para colocar em prática conhecimentos de **SQL, PostgreSQL, desenvolvimento backend, infraestrutura Linux, redes e administração de servidores**, com foco em criar, testar e aprimorar uma solução próxima de cenários reais.

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-10.1%2B-F8DC75?style=flat-square&logo=apachetomcat&logoColor=black)](https://tomcat.apache.org/)
[![Nginx](https://img.shields.io/badge/Nginx-Web%20Server-009639?style=flat-square&logo=nginx&logoColor=white)](https://nginx.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=flat-square&logo=docker&logoColor=white)](https://www.docker.com/)
[![Linux](https://img.shields.io/badge/Linux-Infrastructure-FCC624?style=flat-square&logo=linux&logoColor=black)](https://www.linux.org/)
[![JavaScript](https://img.shields.io/badge/JavaScript-Frontend-F7DF1E?style=flat-square&logo=javascript&logoColor=black)](https://developer.mozilla.org/docs/Web/JavaScript)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)](LICENSE)

---

## 📌 Sobre o projeto

O **DNS & IP Manager** é uma aplicação web para centralizar informações de redes, sub-redes, endereços IP, VLANs, servidores e registros DNS em um único ambiente.

A solução reúne recursos de gerenciamento, autenticação, auditoria e verificação de conectividade, com backend em Java e Spring Boot e persistência em PostgreSQL.

O projeto está em evolução contínua, com implementação e testes das funcionalidades para aprimorar sua confiabilidade e aproximá-lo das necessidades de ambientes reais de infraestrutura.

---

## ✨ Funcionalidades

### 🌐 Redes
- Cadastro e consulta de redes e sub-redes;
- Configuração de máscara de rede e gateway;
- Associação de VLANs;
- Relacionamento entre redes e servidores.

### 🖥️ Servidores
- Cadastro de servidores e endereços IP;
- Identificação por nome;
- Associação a uma rede;
- Controle de status;
- Verificação de conectividade;
- Histórico de verificações.

### 🔢 Endereços IP
- Cadastro e gerenciamento de endereços IP;
- Controle de endereços ocupados e reservados;
- Identificação de IPs disponíveis;
- Busca automática do próximo IP disponível;
- Exclusão dos endereços de rede e broadcast da disponibilidade.

### 🧩 VLANs
- Cadastro e identificação de VLANs;
- Associação de VLANs às redes.

### 🌎 DNS
- Cadastro de registros DNS;
- Definição de nome do host, tipo, valor e TTL;
- Associação de registros a servidores.

### 📊 Dashboard
Indicadores para acompanhar:
- Redes cadastradas;
- Servidores;
- Endereços IP;
- Registros DNS;
- Verificações recentes.

### 🔐 Autenticação
- Login e logout;
- Gerenciamento de sessão HTTP;
- Senhas protegidas com BCrypt;
- Controle de acesso no backend.

Perfis disponíveis:

| Perfil | Permissões |
| --- | --- |
| `ADMIN` | Acesso completo |
| `OPERATOR` | Operações de gerenciamento |
| `VIEWER` | Consulta |

### 👥 Usuários
- Criação e consulta de usuários;
- Definição de perfil;
- Gerenciamento de acesso.

### 📋 Auditoria
Registro de operações importantes, incluindo:
- Login e logout;
- Criação de registros;
- Alterações;
- Operações administrativas;
- Verificações de servidores.

### 📡 Monitoramento
Verificação da conectividade dos servidores cadastrados e armazenamento do histórico no PostgreSQL.

---

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

---

## 🧰 Tecnologias

| Tecnologia | Utilização |
| --- | --- |
| Java 17+ | Linguagem do backend |
| Spring Boot | Framework da aplicação |
| PostgreSQL | Banco de dados relacional |
| Apache Tomcat 10.1+ | Servidor de aplicação |
| Nginx | Servidor web e proxy reverso |
| HTML5, CSS3 e JavaScript | Interface web |
| BCrypt | Hash de senhas |
| REST API | Comunicação entre cliente e backend |
| Docker / Docker Compose | Execução de serviços em contêineres |
| Linux | Ambiente de infraestrutura |
| Maven | Gerenciamento de dependências e build |

---

## 🗄️ Banco de dados

**Banco utilizado:** PostgreSQL

**Nome padrão:** `dns_ip_manager`

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

Recursos de banco utilizados:

- `PRIMARY KEY`
- `FOREIGN KEY`
- `UNIQUE`
- `CHECK`
- `INDEX`
- `VIEW`
- Constraints e relacionamentos

---

## 📁 Estrutura do projeto

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

---

## 🐳 PostgreSQL com Docker

### 📋 Requisitos

- Docker;
- Docker Compose.

Inicie o serviço PostgreSQL:

```bash
docker compose up -d postgres
```

Conecte-se ao banco:

```bash
psql -h localhost -U dnsadmin -d dns_ip_manager
```

> **Segurança:** altere as credenciais padrão antes de qualquer implantação real e nunca publique senhas, tokens ou outros segredos no repositório.

---

## ☕ Backend Java

### 📋 Requisitos

- Java 17 ou superior;
- Maven 3.9 ou superior;
- PostgreSQL.

Compile o projeto:

```bash
cd backend
mvn clean package
```

O arquivo WAR será gerado em:

```text
target/dns-ip-manager.war
```

Para executar localmente:

```bash
mvn spring-boot:run
```

---

## 🐱 Deploy no Apache Tomcat

O projeto gera um arquivo WAR destinado ao Apache Tomcat 10.1 ou superior.

Copie o artefato para o diretório de aplicações do Tomcat:

```bash
sudo cp target/dns-ip-manager.war /opt/tomcat/webapps/
```

Acesso local:

```text
http://localhost:8080/dns-ip-manager/
```

> Confirme o caminho de instalação do Tomcat e a compatibilidade das dependências antes do deploy.

---

## 🌐 Nginx

Arquivo de configuração:

```text
nginx/dns-ip-manager.conf
```

Instale a configuração e valide a sintaxe:

```bash
sudo cp nginx/dns-ip-manager.conf /etc/nginx/conf.d/
sudo nginx -t
sudo systemctl reload nginx
```

Acesso local, conforme a configuração do servidor:

```text
http://localhost/
```

---

## 🔌 REST API

### 🔐 Autenticação

```http
POST /api/auth/login
POST /api/auth/logout
GET  /api/auth/me
```

### 📊 Dashboard

```http
GET /api/dashboard
```

### 🌐 Redes

```http
GET  /api/networks
POST /api/networks
```

### 🖥️ Servidores

```http
GET  /api/servers
POST /api/servers
POST /api/servers/{id}/ping
GET  /api/servers/checks/recent
```

### 🌎 DNS

```http
GET  /api/dns
POST /api/dns
```

### 🔢 IPs disponíveis

```http
GET /api/ip-addresses/available?networkId=1
```

### 👥 Usuários

```http
GET  /api/users
POST /api/users
```

### 📋 Auditoria

```http
GET /api/audit
```

> Os endpoints acima documentam as rotas informadas para o projeto. Consulte a implementação para confirmar requisitos de autenticação, parâmetros e respostas de cada endpoint.

---

## 🔑 Primeiro acesso

Credenciais de laboratório informadas para o projeto:

```text
Usuário: admin
Senha: admin123
Perfil: ADMIN
```

> ⚠️ **Atenção:** essas credenciais são destinadas somente ao laboratório. Altere-as antes de qualquer uso real e evite manter senhas padrão em ambientes acessíveis pela rede.

---

## 🛡️ Segurança

O projeto contempla:

- Autenticação baseada em sessão;
- BCrypt para proteção de senhas;
- Controle de permissões por perfil;
- Proteção das APIs no backend;
- Registro de auditoria;
- Validação de dados;
- Verificação de acesso aos recursos;
- Verificação de conectividade usando o IP previamente cadastrado.

O endpoint de ping foi projetado para não aceitar comandos arbitrários enviados pelo usuário.

**Antes de uma implantação real**, revise configurações, credenciais, permissões, exposição de portas, uso de HTTPS, logs e tratamento de erros.

---

## 🗃️ Scripts SQL

```text
01-schema.sql       → Estrutura principal
02-seed.sql         → Dados iniciais
03-views.sql        → Views e consultas
04-auth.sql         → Autenticação
05-monitoring.sql   → Monitoramento
06-audit.sql        → Auditoria
```

---

## 🎯 Objetivo do projeto

O DNS & IP Manager busca reunir, em uma aplicação, recursos para gerenciamento de redes e infraestrutura, aplicando conhecimentos de:

- SQL e PostgreSQL;
- Java e Spring Boot;
- APIs REST;
- Linux;
- Apache Tomcat;
- Nginx;
- Redes, DNS e VLANs;
- Administração de servidores;
- Segurança e auditoria;
- Docker;
- Git e GitHub.

O cenário foi pensado para se aproximar de necessidades de **infraestrutura e administração de servidores**. A solução continua em desenvolvimento, com testes e melhorias planejadas para evoluir sua confiabilidade e funcionalidade.

---

## 🔮 Próximas evoluções

- 📥 Importação de redes e IPs via CSV;
- 🚨 Sistema de alertas;
- 📧 Notificações por e-mail;
- 🔒 Configuração de HTTPS;
- 🏢 Integração com LDAP / Active Directory;
- 📊 Dashboard avançado;
- 🐳 Containerização completa;
- 🧪 Testes automatizados;
- 📈 Histórico avançado de disponibilidade;
- 💾 Backup e restauração do banco;
- 🔐 Permissões mais detalhadas;
- 📱 Melhorias de responsividade.

---

## 👨‍💻 Autor

**Matheus Marks**

Projeto idealizado e desenvolvido por mim para criar uma solução de gerenciamento de endereços IP, redes, registros DNS e infraestrutura de servidores. Atualmente, estou trabalhando para implementar a solução no ambiente real, testar suas funcionalidades e aprimorar continuamente o sistema, buscando torná-lo cada vez mais completo, confiável e adequado às necessidades do dia a dia.

---

## ⭐ Apoie o projeto

Se este projeto foi útil para você, considere deixar uma ⭐ no repositório.

---

## 📄 Licença

Este projeto está disponível sob a licença **MIT**. Consulte o arquivo [`LICENSE`](LICENSE) para obter os termos completos.
