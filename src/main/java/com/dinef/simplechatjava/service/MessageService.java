package com.dinef.simplechatjava.service;

import com.dinef.simplechatjava.model.Message;
import com.dinef.simplechatjava.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // New method to fetch all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
