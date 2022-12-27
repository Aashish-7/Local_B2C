package com.b2c.Local.B2C.websocket.buffermessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository @Transactional
public interface BufferMessagesRepository extends JpaRepository<BufferMessages, UUID> {
}