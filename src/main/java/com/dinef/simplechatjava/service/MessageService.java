package com.dinef.simplechatjava.service;

import org.springframework.stereotype.Service;
import com.dinef.simplechatjava.repository.MessageRepository;
import com.dinef.simplechatjava.model.Message;


@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // Other methods, e.g., getMessagesByUser(User user)
}
