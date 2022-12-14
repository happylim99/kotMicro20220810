package com.sean.discovery.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurity {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.csrf().ignoringAntMatchers("/eureka/**")
        return http.build()
    }

}