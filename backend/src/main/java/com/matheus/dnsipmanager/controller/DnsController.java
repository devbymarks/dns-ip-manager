package com.matheus.dnsipmanager.controller;

import com.matheus.dnsipmanager.model.DnsRecord;
import com.matheus.dnsipmanager.repository.DnsRecordRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dns")
public class DnsController {
    private final DnsRecordRepository repository;
    public DnsController(DnsRecordRepository repository){this.repository=repository;}

    @GetMapping public List<DnsRecord> all(){return repository.findAll();}
    @PostMapping public DnsRecord create(@RequestBody DnsRecord record){return repository.save(record);}
}
