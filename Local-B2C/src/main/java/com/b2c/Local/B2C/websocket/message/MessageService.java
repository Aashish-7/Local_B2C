package com.b2c.Local.B2C.websocket.message;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import com.b2c.Local.B2C.store.model.LocalStore;
import com.b2c.Local.B2C.websocket.dto.MessageSenderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class MessageService {

    DefaultSimpUserRegistry defaultSimpUserRegistry;

    UserRepository userRepository;

    MessageRepository messageRepository;

    SimpMessagingTemplate simpMessagingTemplate;

    HttpSession httpSession;

    LocalStoreRepository localStoreRepository;

    @Autowired
    public MessageService(DefaultSimpUserRegistry defaultSimpUserRegistry, UserRepository userRepository, MessageRepository messageRepository, SimpMessagingTemplate simpMessagingTemplate, HttpSession httpSession, LocalStoreRepository localStoreRepository) {
        this.defaultSimpUserRegistry = defaultSimpUserRegistry;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.httpSession = httpSession;
        this.localStoreRepository = localStoreRepository;
    }

    public void sendMsgToLocalStoreOwner(MessageSenderDto messageDto) {
        LocalStore localStore = getLocalStoreByLocalStoreId(messageDto.getLocalStoreId());
        if (getLocalStoreOwnerOnline(localStore.getUser())) {
            simpMessagingTemplate.convertAndSendToUser(localStore.getUser().getEmail(), "/user/" + messageDto.getLocalStoreId(), messageDto);
            saveMessage(messageDto, true, "/user/" + messageDto.getLocalStoreId());
        } else {
            saveMessage(messageDto, false, "/user/" + messageDto.getLocalStoreId());
        }
    }

    public void saveMessage(MessageSenderDto messageDto, boolean msgSend, String topic) {
        Message message = new Message();
        message.setMessage(messageDto.getMsg());
        message.setSubscriptionTopic(topic);
        message.setSendTime(LocalDateTime.now());
        message.setSender(userRepository.findByEmail(messageDto.getSender()));
        message.setReceiver(getLocalStoreByLocalStoreId(messageDto.getLocalStoreId()).getUser());
        message.setHttpSessionId(httpSession.getId());
        message.setMsgSend(msgSend);
        messageRepository.save(message);
    }

    public LocalStore getLocalStoreByLocalStoreId(UUID localStoreId) {
        if (localStoreRepository.existsByIdAndActiveTrue(localStoreId)) {
            return localStoreRepository.findByIdAndActiveTrue(localStoreId);
        } else {
            throw new NotFound404Exception("LocalStore Not Found And Message Not Send");
        }
    }

    public boolean getLocalStoreOwnerOnline(User user) {
        if (Objects.nonNull(defaultSimpUserRegistry.getUser(user.getEmail()))) {
            return true;
        } else {
            return false;
        }
    }
}
