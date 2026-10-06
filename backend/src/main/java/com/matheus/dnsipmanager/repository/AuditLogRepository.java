package com.matheus.dnsipmanager.repository;
import com.matheus.dnsipmanager.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuditLogRepository extends JpaRepository<AuditLog,Long>{}
