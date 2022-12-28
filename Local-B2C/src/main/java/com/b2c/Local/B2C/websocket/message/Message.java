package com.b2c.Local.B2C.websocket.message;

import com.b2c.Local.B2C.auths.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity @Getter @Setter
public class Message {

    @Id
    private String mId = UUID.randomUUID().toString();

    private LocalDateTime sendTime;

    private String subscriptionTopic;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBlobType")
    private Object message;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

    private String httpSessionId;

    private Boolean msgSend;
}
