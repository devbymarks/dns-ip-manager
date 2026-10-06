package com.matheus.dnsipmanager.model;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity @Table(name="server_checks")
public class ServerCheck {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="server_id",nullable=false) private Server server;
    private String status;
    @Column(name="response_time") private Long responseTime;
    @Column(name="checked_at") private OffsetDateTime checkedAt;
    private String message;
    public Long getId(){return id;} public Server getServer(){return server;} public void setServer(Server v){server=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public Long getResponseTime(){return responseTime;} public void setResponseTime(Long v){responseTime=v;}
    public OffsetDateTime getCheckedAt(){return checkedAt;} public void setCheckedAt(OffsetDateTime v){checkedAt=v;}
    public String getMessage(){return message;} public void setMessage(String v){message=v;}
}
