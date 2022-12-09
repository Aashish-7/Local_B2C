package com.b2c.Local.B2C.securities.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToOne
    private FilterRequest filterRequest;

    public RequestResponseBody(Object requestBody, FilterRequest filterRequest, Object responseBody) {
        this.requestBody = requestBody;
        this.filterRequest = filterRequest;
        this.responseBody = responseBody;
    }
}
