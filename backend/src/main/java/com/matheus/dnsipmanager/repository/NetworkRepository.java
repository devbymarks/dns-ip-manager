package com.matheus.dnsipmanager.repository;

import com.matheus.dnsipmanager.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<Network, Long> {}
