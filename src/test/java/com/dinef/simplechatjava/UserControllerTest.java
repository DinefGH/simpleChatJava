package com.dinef.simplechatjava;

import com.dinef.simplechatjava.controller.UserController;
import com.dinef.simplechatjava.model.User;
import com.dinef.simplechatjava.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean; // Deprecated but still usable
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock the UserService so we don't need the actual DB or logic
    @MockBean
    private UserService userService;

    @Test
    public void givenValidUser_whenRegister_thenReturns200AndUser() throws Exception {
        // Arrange: Create a User object to simulate the saved user
        User savedUser = new User("john_doe", "john@example.com", "encodedPassword");
        // Suppose the DB assigns ID 1
        savedUser.setId(1L);

        // Mock the service layer to return our "saved" user
        Mockito.when(userService.register(any(User.class))).thenReturn(savedUser);

        // Prepare JSON payload for the request (raw password in this example)
        String userJson = """
            {
                "username": "john_doe",
                "email": "john@example.com",
                "password": "plaintextPassword"
            }
            """;

        // Act & Assert
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("john_doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }
}
