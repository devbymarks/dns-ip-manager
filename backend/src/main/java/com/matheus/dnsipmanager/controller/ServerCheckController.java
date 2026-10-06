package com.matheus.dnsipmanager.controller;
import com.matheus.dnsipmanager.model.*;
import com.matheus.dnsipmanager.repository.*;
import com.matheus.dnsipmanager.service.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.*;
import java.time.*;
import java.util.*;
@RestController @RequestMapping("/api/servers")
public class ServerCheckController {
    private final ServerRepository servers; private final ServerCheckRepository checks; private final PermissionService permission; private final AuditService audit;
    public ServerCheckController(ServerRepository s,ServerCheckRepository c,PermissionService p,AuditService a){servers=s;checks=c;permission=p;audit=a;}
    @PostMapping("/{id}/ping")
    public ResponseEntity<?> ping(@PathVariable Long id,HttpSession session,HttpServletRequest req){
        if(!permission.allowed(session,"ADMIN","OPERATOR"))return ResponseEntity.status(403).body(Map.of("error","Acesso negado"));
        Server server=servers.findById(id).orElse(null); if(server==null)return ResponseEntity.notFound().build();
        ServerCheck c=new ServerCheck();c.setServer(server);c.setCheckedAt(OffsetDateTime.now());
        long start=System.nanoTime(); boolean ok=false; String msg;
        try{InetAddress a=InetAddress.getByName(server.getIp());ok=a.isReachable(3000);msg=ok?"Servidor respondeu":"Sem resposta";}catch(Exception e){msg="Falha na verificação";}
        c.setStatus(ok?"ONLINE":"OFFLINE");c.setResponseTime(ok?(System.nanoTime()-start)/1_000_000:null);c.setMessage(msg);checks.save(c);
        audit.log(req,(String)session.getAttribute("user"),"PING","SERVER",id,"{\"status\":\""+c.getStatus()+"\"}");
        return ResponseEntity.ok(Map.of("serverId",id,"hostname",server.getHostname(),"ip",server.getIp(),"status",c.getStatus(),"responseTime",c.getResponseTime()==null?0:c.getResponseTime(),"message",msg));
    }
    @GetMapping("/checks/recent")
    public ResponseEntity<?> recent(HttpSession s){if(!permission.allowed(s,"ADMIN","OPERATOR","VIEWER"))return ResponseEntity.status(403).build();return ResponseEntity.ok(checks.findTop20ByOrderByCheckedAtDesc());}
}
