package com.b2c.Local.B2C.securities.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "request_response_body")
public class RequestResponseBody {

    @Id
    private String id = UUID.randomUUID().toString();

    private Object requestBody;

    private Object responseBody;

    private String sessionId;

    private String userPrincipal;

    private Date lastAccessTime;

    @OneToOne
    private FilterRequest filterRequest;

    public RequestResponseBody(Object requestBody, String sessionId, String userPrincipal, FilterRequest filterRequest, Date lastAccessTime, Object responseBody) {
        this.requestBody = requestBody;
        this.sessionId = sessionId;
        this.userPrincipal = userPrincipal;
        this.filterRequest = filterRequest;
        this.lastAccessTime = lastAccessTime;
        this.responseBody = responseBody;
    }
}
