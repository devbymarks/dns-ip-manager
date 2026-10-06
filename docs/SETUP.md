# Instalação completa

## 1. PostgreSQL

```bash
docker compose up -d postgres
```

## 2. Java

```bash
java -version
```

Use Java 17 ou superior.

## 3. Compilar

```bash
cd backend
mvn clean package
```

## 4. Tomcat

```bash
sudo cp target/dns-ip-manager.war /opt/tomcat/webapps/
```

## 5. Nginx

```bash
sudo cp nginx/dns-ip-manager.conf /etc/nginx/conf.d/
sudo nginx -t
sudo systemctl reload nginx
```

## 6. Acesso

```text
http://localhost/
```

Laboratório:

```text
admin / admin123
```

Troque a senha em ambiente real.
