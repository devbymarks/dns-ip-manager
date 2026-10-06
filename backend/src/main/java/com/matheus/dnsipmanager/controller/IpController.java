package com.matheus.dnsipmanager.controller;
import com.matheus.dnsipmanager.service.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/ip-addresses")
public class IpController {
    private final JdbcTemplate jdbc; private final PermissionService permission;
    public IpController(JdbcTemplate j,PermissionService p){jdbc=j;permission=p;}
    @GetMapping("/available")
    public ResponseEntity<?> available(@RequestParam Long networkId,HttpSession s){
        if(!permission.allowed(s,"ADMIN","OPERATOR","VIEWER"))return ResponseEntity.status(403).build();
        String sql="""
            WITH net AS (SELECT cidr, gateway FROM networks WHERE id=?)
            SELECT host(g) AS ip
            FROM net, LATERAL generate_series(
                network(cidr)::inet + 1,
                broadcast(cidr)::inet - 1,
                1
            ) g
            WHERE g <> gateway
              AND NOT EXISTS (SELECT 1 FROM servers x WHERE x.ip=g)
              AND NOT EXISTS (SELECT 1 FROM ip_addresses i WHERE i.ip=g AND i.status IN ('OCUPADO','RESERVADO'))
            ORDER BY g LIMIT 1
            """;
        List<Map<String,Object>> rows=jdbc.queryForList(sql,networkId);
        return ResponseEntity.ok(rows.isEmpty()?Map.of("available",false):Map.of("available",true,"ip",rows.get(0).get("ip")));
    }
}
