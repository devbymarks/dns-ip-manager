# Tomcat

O backend é empacotado como WAR para Tomcat 10.1+.

1. Instale Java 17.
2. Instale Tomcat 10.1+.
3. Compile:

```bash
cd backend
mvn clean package
```

4. Copie o WAR:

```bash
sudo cp target/dns-ip-manager.war /opt/tomcat/webapps/
```

5. Garanta que o Tomcat tenha acesso ao PostgreSQL através das variáveis:

```bash
DB_URL=jdbc:postgresql://127.0.0.1:5432/dns_ip_manager
DB_USER=dnsadmin
DB_PASSWORD=ALTERE-ESTA-SENHA
```

URL:

```text
http://localhost:8080/dns-ip-manager/
```
