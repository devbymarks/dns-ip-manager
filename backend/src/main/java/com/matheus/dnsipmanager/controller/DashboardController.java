package com.matheus.dnsipmanager.controller;

import com.matheus.dnsipmanager.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final NetworkRepository networks;
    private final ServerRepository servers;
    private final DnsRecordRepository dns;

    public DashboardController(NetworkRepository networks, ServerRepository servers, DnsRecordRepository dns){
        this.networks=networks; this.servers=servers; this.dns=dns;
    }

    @GetMapping
    public Map<String,Long> dashboard(){
        return Map.of(
            "networks", networks.count(),
            "servers", servers.count(),
            "activeServers", servers.countByStatus("ATIVO"),
            "dnsRecords", dns.count()
        );
    }
}
