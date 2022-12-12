package com.b2c.Local.B2C.securities.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "request_response_body")
public class RequestResponseBody {

    @Id
    private String id;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonType")
    private Object requestBody;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonType")
    private Object responseBody;

    @OneToOne
    private FilterRequest filterRequest;

    public RequestResponseBody(String id,Object requestBody, FilterRequest filterRequest, Object responseBody) {
        this.id = id;
        this.requestBody = requestBody;
        this.filterRequest = filterRequest;
        this.responseBody = responseBody;
    }
}
