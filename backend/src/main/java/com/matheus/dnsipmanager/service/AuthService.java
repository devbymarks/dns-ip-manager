package com.matheus.dnsipmanager.service;
import com.matheus.dnsipmanager.model.User;
import com.matheus.dnsipmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    private final UserRepository users; private final PasswordEncoder encoder;
    public AuthService(UserRepository users, PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
    public User authenticate(String username,String password){
        return users.findByUsername(username)
            .filter(User::isActive)
            .filter(u -> encoder.matches(password,u.getPasswordHash()))
            .orElse(null);
    }
}
