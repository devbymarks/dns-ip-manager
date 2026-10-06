# Arquitetura

## Camadas

### PostgreSQL
Responsável pela persistência e regras de integridade.

### Java / Spring Boot
Expõe a API REST e acessa o PostgreSQL usando Spring Data JPA.

### Tomcat
Executa o WAR da aplicação.

### Nginx
Atua como reverse proxy e ponto de entrada HTTP.

### Frontend
HTML, CSS e JavaScript consome a API REST.

## Fluxo

```text
Browser
   |
   v
Nginx
   |
   v
Tomcat
   |
   v
Spring Boot
   |
   v
PostgreSQL
```
