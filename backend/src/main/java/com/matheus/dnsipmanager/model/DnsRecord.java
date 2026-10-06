package com.matheus.dnsipmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name="dns_records")
public class DnsRecord {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String hostname;
    private String domain;
    private String ip;
    @Column(name="record_type")
    private String recordType = "A";
    private Integer ttl = 300;

    public Long getId(){return id;}
    public String getHostname(){return hostname;}
    public void setHostname(String v){hostname=v;}
    public String getDomain(){return domain;}
    public void setDomain(String v){domain=v;}
    public String getIp(){return ip;}
    public void setIp(String v){ip=v;}
    public String getRecordType(){return recordType;}
    public void setRecordType(String v){recordType=v;}
    public Integer getTtl(){return ttl;}
    public void setTtl(Integer v){ttl=v;}
}
