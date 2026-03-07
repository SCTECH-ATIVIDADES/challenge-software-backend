package com.api.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${app.user.username}")
    private String username;

    @Value("${app.user.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.username.equals(username)) {
            return User.builder()
                    .username(this.username)
                    .password("{noop}" + this.password) // NoOpPasswordEncoder for simplicity
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}