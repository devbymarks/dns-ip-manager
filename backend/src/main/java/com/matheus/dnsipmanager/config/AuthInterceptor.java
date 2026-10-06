package com.matheus.dnsipmanager.config;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override public boolean preHandle(HttpServletRequest req,HttpServletResponse res,Object handler)throws Exception{
        String uri=req.getRequestURI();
        if(uri.contains("/api/auth/")) return true;
        if(uri.contains("/api/") && req.getSession(false)!=null && req.getSession(false).getAttribute("user")!=null)return true;
        if(uri.contains("/api/")){res.setStatus(401);res.setContentType("application/json");res.getWriter().write("{\"error\":\"Autenticação necessária\"}");return false;}
        return true;
    }
}
