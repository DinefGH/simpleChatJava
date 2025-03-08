package com.dinef.simplechatjava.security;

import com.dinef.simplechatjava.model.User;
import com.dinef.simplechatjava.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This is where Spring Security looks up the user by username.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ensure you have a method like findByUsername in your UserRepository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new CustomUserDetails(user);
    }
}
