package com.b2c.Local.B2C.websocket.buffermessage;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BufferMessagesService {

    BufferMessagesRepository bufferMessagesRepository;

    UserRepository userRepository;

    @Autowired
    public BufferMessagesService(BufferMessagesRepository bufferMessagesRepository, UserRepository userRepository) {
        this.bufferMessagesRepository = bufferMessagesRepository;
        this.userRepository = userRepository;
    }

    public void saveBufferMessages(String message, String sendBy, String receivedBy){
        BufferMessages bufferMessages = new BufferMessages();
        bufferMessages.setMessage(message);
        bufferMessages.setSender(userRepository.findByUsername(sendBy));
        bufferMessages.setReceiver(userRepository.findByUsername(receivedBy));
        bufferMessagesRepository.save(bufferMessages);
    }

}
