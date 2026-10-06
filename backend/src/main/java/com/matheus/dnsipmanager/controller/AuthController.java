package com.matheus.dnsipmanager.controller;
import com.matheus.dnsipmanager.model.User;
import com.matheus.dnsipmanager.service.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth; private final AuditService audit;
    public AuthController(AuthService a,AuditService audit){this.auth=a;this.audit=audit;}
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body,HttpServletRequest req,HttpSession session){
        User u=auth.authenticate(body.getOrDefault("username",""),body.getOrDefault("password",""));
        if(u==null){audit.log(req,body.get("username"),"LOGIN","AUTH",null,"{\"success\":false}");return ResponseEntity.status(401).body(Map.of("error","Usuário ou senha inválidos"));}
        session.setAttribute("user",u.getUsername()); session.setAttribute("role",u.getRole());
        audit.log(req,u.getUsername(),"LOGIN","AUTH",u.getId(),"{\"success\":true}");
        return ResponseEntity.ok(Map.of("username",u.getUsername(),"fullName",u.getFullName(),"role",u.getRole()));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req,HttpSession session){
        String user=(String)session.getAttribute("user"); audit.log(req,user,"LOGOUT","AUTH",null,"{}"); session.invalidate();
        return ResponseEntity.ok(Map.of("message","Logout realizado"));
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session){
        if(session.getAttribute("user")==null)return ResponseEntity.status(401).body(Map.of("authenticated",false));
        return ResponseEntity.ok(Map.of("authenticated",true,"username",session.getAttribute("user"),"role",session.getAttribute("role")));
    }
}
