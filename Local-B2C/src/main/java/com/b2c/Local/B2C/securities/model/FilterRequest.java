package com.b2c.Local.B2C.securities.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
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

    private LocalDateTime localDateTime;

    private String contentType;

    private String url;

    private String sessionId;

    private String userAgent;

    private Boolean newSession;

    private String userId;

    private Date lastAccessTime;

    private String userName;
}
