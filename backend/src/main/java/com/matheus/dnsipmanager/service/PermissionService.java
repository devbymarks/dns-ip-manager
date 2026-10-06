package com.matheus.dnsipmanager.service;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
@Service
public class PermissionService {
    public boolean logged(HttpSession s){return s.getAttribute("user")!=null;}
    public boolean allowed(HttpSession s,String... roles){
        Object u=s.getAttribute("user"); if(u==null)return false;
        String role=(String)s.getAttribute("role");
        for(String r:roles)if(r.equals(role))return true;
        return false;
    }
}
