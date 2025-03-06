package com.dinef.simplechatjava.controller;

import com.dinef.simplechatjava.model.User;
import com.dinef.simplechatjava.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Spring injects the UserService via constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // user JSON must now include a "password" field
        User savedUser = userService.register(user);
        // Typically, you would avoid returning the password back
        savedUser.setPassword(null);
        return ResponseEntity.ok(savedUser);
    }
}
