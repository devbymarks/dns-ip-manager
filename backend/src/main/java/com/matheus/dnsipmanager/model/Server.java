package com.matheus.dnsipmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name="servers")
public class Server {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String hostname;
    @Column(nullable=false, unique=true)
    private String ip;
    @Column(name="operating_system")
    private String operatingSystem;
    private String status;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="network_id", nullable=false)
    private Network network;

    public Long getId(){return id;}
    public String getHostname(){return hostname;}
    public void setHostname(String v){hostname=v;}
    public String getIp(){return ip;}
    public void setIp(String v){ip=v;}
    public String getOperatingSystem(){return operatingSystem;}
    public void setOperatingSystem(String v){operatingSystem=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public Network getNetwork(){return network;}
    public void setNetwork(Network v){network=v;}
}
