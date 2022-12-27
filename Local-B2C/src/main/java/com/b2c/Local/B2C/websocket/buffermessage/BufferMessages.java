package com.b2c.Local.B2C.websocket.buffermessage;

import com.b2c.Local.B2C.auths.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BufferMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String message;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

    private LocalDateTime localDateTime = LocalDateTime.now();

}
