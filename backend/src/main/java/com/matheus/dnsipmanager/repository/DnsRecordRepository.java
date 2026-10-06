package com.matheus.dnsipmanager.repository;

import com.matheus.dnsipmanager.model.DnsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnsRecordRepository extends JpaRepository<DnsRecord, Long> {}
