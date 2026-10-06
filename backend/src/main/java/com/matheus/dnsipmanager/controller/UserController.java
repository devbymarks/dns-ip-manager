package com.matheus.dnsipmanager.controller;
import com.matheus.dnsipmanager.model.User;
import com.matheus.dnsipmanager.repository.UserRepository;
import com.matheus.dnsipmanager.service.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo; private final PasswordEncoder encoder; private final PermissionService permission; private final AuditService audit;
    public UserController(UserRepository r,PasswordEncoder e,PermissionService p,AuditService a){repo=r;encoder=e;permission=p;audit=a;}
    @GetMapping public ResponseEntity<?> all(HttpSession s){if(!permission.allowed(s,"ADMIN"))return ResponseEntity.status(403).body(Map.of("error","Acesso negado"));return ResponseEntity.ok(repo.findAll().stream().map(u->Map.of("id",u.getId(),"username",u.getUsername(),"fullName",u.getFullName(),"email",Objects.toString(u.getEmail(),""),"role",u.getRole(),"active",u.isActive())).toList());}
    @PostMapping public ResponseEntity<?> create(@RequestBody Map<String,String> b,HttpSession s,HttpServletRequest req){
        if(!permission.allowed(s,"ADMIN"))return ResponseEntity.status(403).body(Map.of("error","Acesso negado"));
        User u=new User();u.setUsername(b.get("username"));u.setFullName(b.get("fullName"));u.setEmail(b.get("email"));u.setRole(b.getOrDefault("role","VIEWER"));u.setActive(true);u.setPasswordHash(encoder.encode(b.get("password")));
        User saved=repo.save(u);audit.log(req,(String)s.getAttribute("user"),"CREATE","USER",saved.getId(),"{}");return ResponseEntity.status(201).body(Map.of("id",saved.getId(),"username",saved.getUsername()));
    }
}
