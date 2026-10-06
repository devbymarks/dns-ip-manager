package com.matheus.dnsipmanager.controller;
import com.matheus.dnsipmanager.repository.AuditLogRepository;
import com.matheus.dnsipmanager.service.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/audit")
public class AuditController {
    private final AuditLogRepository repo; private final PermissionService p;
    public AuditController(AuditLogRepository r,PermissionService p){repo=r;this.p=p;}
    @GetMapping public ResponseEntity<?> all(HttpSession s){
        if(!p.allowed(s,"ADMIN"))return ResponseEntity.status(403).body(Map.of("error","Acesso negado"));
        return ResponseEntity.ok(repo.findAll());
    }
}
