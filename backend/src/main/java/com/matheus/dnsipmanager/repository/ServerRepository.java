package com.matheus.dnsipmanager.repository;

import com.matheus.dnsipmanager.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    long countByStatus(String status);
}
