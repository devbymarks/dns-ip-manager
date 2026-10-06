package com.matheus.dnsipmanager.service;
import com.matheus.dnsipmanager.model.AuditLog;
import com.matheus.dnsipmanager.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
@Service
public class AuditService {
    private final AuditLogRepository repo;
    public AuditService(AuditLogRepository repo){this.repo=repo;}
    public void log(HttpServletRequest req,String username,String action,String entity,Long id,String details){
        AuditLog a=new AuditLog(); a.setUsername(username); a.setAction(action); a.setEntityName(entity);
        a.setEntityId(id); a.setDetails(details==null?"{}":details); a.setSourceIp(req.getRemoteAddr()); repo.save(a);
    }
}
