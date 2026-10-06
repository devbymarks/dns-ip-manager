package com.matheus.dnsipmanager.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "networks")
public class Network {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String name;
    @Column(nullable=false, unique=true)
    private String cidr;
    private String gateway;
    private String description;
    @Column(name="created_at")
    private OffsetDateTime createdAt;

    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getCidr(){return cidr;}
    public void setCidr(String v){cidr=v;}
    public String getGateway(){return gateway;}
    public void setGateway(String v){gateway=v;}
    public String getDescription(){return description;}
    public void setDescription(String v){description=v;}
    public OffsetDateTime getCreatedAt(){return createdAt;}
}
