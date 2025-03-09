package com.dinef.simplechatjava.controller;

import com.dinef.simplechatjava.dto.LoginRequest;
import com.dinef.simplechatjava.dto.LoginResponse;
import com.dinef.simplechatjava.security.CustomUserDetails;
import com.dinef.simplechatjava.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        // Assuming your userDetails is of type CustomUserDetails
        CustomUserDetails customUser = (CustomUserDetails) userDetails;
        final String jwt = jwtUtil.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse(
                jwt,
                customUser.getId(),
                customUser.getUsername(),
                customUser.getEmail()
        );
        return ResponseEntity.ok(loginResponse);
    }
}
