package com.matheus.dnsipmanager.config;

import com.matheus.dnsipmanager.model.User;
import com.matheus.dnsipmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {
    @Bean
    CommandLineRunner createLabAdmin(UserRepository repository, PasswordEncoder encoder) {
        return args -> {
            if (repository.findByUsername("admin").isEmpty()) {
                User u = new User();
                u.setUsername("admin");
                u.setFullName("Administrador");
                u.setEmail("admin@example.local");
                u.setRole("ADMIN");
                u.setActive(true);
                u.setPasswordHash(encoder.encode("admin123"));
                repository.save(u);
            }
        };
    }
}
