package com.b2c.Local.B2C.websocket.message;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import com.b2c.Local.B2C.websocket.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.session.MapSession;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

import java.time.LocalDateTime;

@Service
public class MessageService {

    DefaultSimpUserRegistry defaultSimpUserRegistry;

    UserRepository userRepository;

    MessageRepository messageRepository;

    SimpMessagingTemplate simpMessagingTemplate;

    MapSession mapSession;

    LocalStoreRepository localStoreRepository;

    @Autowired
    public MessageService(DefaultSimpUserRegistry defaultSimpUserRegistry, UserRepository userRepository, MessageRepository messageRepository, SimpMessagingTemplate simpMessagingTemplate, MapSession mapSession, LocalStoreRepository localStoreRepository) {
        this.defaultSimpUserRegistry = defaultSimpUserRegistry;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.mapSession = mapSession;
        this.localStoreRepository = localStoreRepository;
    }

    public void sendMsgToUser(MessageDto messageDto){
        simpMessagingTemplate.convertAndSendToUser("","/user",messageDto);
    }
    public void saveMessage(MessageDto messageDto, boolean msgSend,String topic){
        Message message = new Message();
        message.setMessage(messageDto.getMsg());
        message.setSubscriptionTopic(topic);
        message.setSendTime(LocalDateTime.now());
        message.setSender(userRepository.findByEmail(messageDto.getSender()));
        message.setReceiver(userRepository.findByEmail(""));
        message.setHttpSessionId(mapSession.getId());
        message.setMsgSend(msgSend);
        messageRepository.save(message);
    }
}
