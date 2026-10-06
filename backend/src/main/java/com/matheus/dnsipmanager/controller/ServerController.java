package com.matheus.dnsipmanager.controller;

import com.matheus.dnsipmanager.model.Server;
import com.matheus.dnsipmanager.repository.ServerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servers")
public class ServerController {
    private final ServerRepository repository;
    public ServerController(ServerRepository repository){this.repository=repository;}

    @GetMapping public List<Server> all(){return repository.findAll();}
    @PostMapping public Server create(@RequestBody Server server){return repository.save(server);}
    @GetMapping("/count") public Map<String,Long> count(){
        return Map.of(
            "total", repository.count(),
            "active", repository.countByStatus("ATIVO"),
            "inactive", repository.countByStatus("INATIVO")
        );
    }
}
