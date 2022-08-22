package com.sean.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class AuthBeanConfig {

    @Bean
    fun bcrypt() = BCryptPasswordEncoder()

}