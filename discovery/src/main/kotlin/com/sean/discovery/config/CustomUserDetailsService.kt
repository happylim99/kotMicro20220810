package com.sean.discovery.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class CustomUserDetailsService {

    @Bean
    fun users(): UserDetailsService {
        val user = User.builder()
            .username("user")
            .password("{bcrypt}\$2a\$10\$3drzNTvo9jMBE5hWFW1zW..TN9e83Mo7QxCFSLrMH9lJk/B2XyT52")
            .roles("USER")
            .build()
        val admin = User.builder()
            .username("admin")
            .password("{bcrypt}\$2a\$10\$3drzNTvo9jMBE5hWFW1zW..TN9e83Mo7QxCFSLrMH9lJk/B2XyT52")
            .roles("USER", "ADMIN")
            .build()
        return InMemoryUserDetailsManager(user, admin)
    }
}