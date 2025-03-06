package com.dinef.simplechatjava;

import com.dinef.simplechatjava.model.Message;
import com.dinef.simplechatjava.model.User;
import com.dinef.simplechatjava.service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.dinef.simplechatjava.controller.MessageController;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void whenSendMessage_thenReturnsMessage() throws Exception {
        // Arrange: Create a dummy message and a user.
        User user = new User("john_doe", "john@example.com", "password123");
        Message message = new Message("Hello, world!", user);
        message.setId(1L); // Simulate generated ID.

        // When the service's saveMessage is called, return our dummy message.
        Mockito.when(messageService.saveMessage(any(Message.class))).thenReturn(message);

        // Act & Assert: Perform a POST request and verify the response.
        String jsonPayload = "{\"text\":\"Hello, world!\",\"user\":{\"id\":1}}"; // Assuming you pass user ID.
        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.text").value("Hello, world!"));
    }
}