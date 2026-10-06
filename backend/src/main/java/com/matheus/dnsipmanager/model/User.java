package com.matheus.dnsipmanager.model;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity @Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true) private String username;
    @Column(name="password_hash",nullable=false) private String passwordHash;
    @Column(name="full_name",nullable=false) private String fullName;
    private String email;
    @Column(nullable=false) private String role="VIEWER";
    @Column(nullable=false) private boolean active=true;
    @Column(name="created_at") private OffsetDateTime createdAt;
    @Column(name="updated_at") private OffsetDateTime updatedAt;
    public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getRole(){return role;} public void setRole(String v){role=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
