package com.sean.sample_ws.config.security

import com.sean.auth.config.WebSecurity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
class CustomWebSecurity: WebSecurity() {

    @Bean
    override fun configure(http: HttpSecurity): SecurityFilterChain {
        super.securityFilterChain(http)
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/user/testing").permitAll()
//            .anyRequest().authenticated()
            .anyRequest().permitAll()
        return http.build()
    }

}