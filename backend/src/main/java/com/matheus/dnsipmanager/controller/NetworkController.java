package com.matheus.dnsipmanager.controller;

import com.matheus.dnsipmanager.model.Network;
import com.matheus.dnsipmanager.repository.NetworkRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/networks")
public class NetworkController {
    private final NetworkRepository repository;
    public NetworkController(NetworkRepository repository){this.repository=repository;}

    @GetMapping public List<Network> all(){return repository.findAll();}
    @PostMapping public Network create(@RequestBody Network network){return repository.save(network);}
}
