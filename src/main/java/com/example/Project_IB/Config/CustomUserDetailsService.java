package com.example.Project_IB.Config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("admin".equals(username)) {
            String encodedPassword = passwordEncoder.encode("admin"); // Encode the password
            return User.withUsername("admin")
                    .password(encodedPassword)
                    .roles("ADMIN")
                    .build();
        } else if ("employee".equals(username)) {
            String encodedPassword = passwordEncoder.encode("employee"); // Encode the password
            return User.withUsername("employee")
                    .password(encodedPassword)
                    .roles("EMPLOYEE")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
