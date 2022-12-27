package com.b2c.Local.B2C.notification.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity @Getter @Setter @NoArgsConstructor @Table
public class UserNotification implements Serializable {

    @Id
    private UUID id = UUID.randomUUID();

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonType")
    private Object notifyMsg;

    private Boolean active;

    private String url;
}
