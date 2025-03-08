package com.dinef.simplechatjava.controller;

import com.dinef.simplechatjava.model.User;
import com.dinef.simplechatjava.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User savedUser = userService.register(user);
        savedUser.setPassword(null);
        return ResponseEntity.ok(savedUser);
    }
}
