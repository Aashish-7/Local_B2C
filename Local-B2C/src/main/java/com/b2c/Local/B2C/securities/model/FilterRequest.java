package com.b2c.Local.B2C.securities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "filter_request")
public class FilterRequest {

    @Id
    private String requestId = UUID.randomUUID().toString();

    private String remoteIp;

    private Integer remotePort;

    private String remoteHost;

    private String protocol;

    private String contentType;

    private String url;

    @JsonIgnore
    private String sessionId;

    private String userAgent;

    @JsonIgnore
    private Boolean newSession;

    private String userId;

    private Date lastAccessTime;

    private String userName;

    private String macAddress;
}
