package com.matheus.dnsipmanager.model;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity @Table(name="audit_log")
public class AuditLog {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String username;
    private String action;
    @Column(name="entity_name") private String entityName;
    @Column(name="entity_id") private Long entityId;
    @Column(columnDefinition="jsonb") private String details;
    @Column(name="source_ip") private String sourceIp;
    @Column(name="created_at") private OffsetDateTime createdAt;
    public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getAction(){return action;} public void setAction(String v){action=v;}
    public String getEntityName(){return entityName;} public void setEntityName(String v){entityName=v;}
    public Long getEntityId(){return entityId;} public void setEntityId(Long v){entityId=v;}
    public String getDetails(){return details;} public void setDetails(String v){details=v;}
    public String getSourceIp(){return sourceIp;} public void setSourceIp(String v){sourceIp=v;}
}
