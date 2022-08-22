package com.sean.auth.config

import com.sean.base.util.SpringContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//@Configuration
//@ComponentScan(basePackages= ["com.sean"])
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//    prePostEnabled = true,
//    securedEnabled = true,
//    jsr250Enabled = true
//)
abstract class WebSecurity {

    @Bean
    @Throws(Exception::class)
    open fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    abstract fun configure(http: HttpSecurity): SecurityFilterChain

//    @Bean
//    @Order(Ordered.LOWEST_PRECEDENCE)
//    fun configure(http: HttpSecurity): SecurityFilterChain {
////        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
////        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bcrypt)
//
//        http.csrf().disable()
//            .authorizeRequests()
////            .accessDecisionManager(accessDecisionManager())
////            .expressionHandler(webSecurityExpressionHandler())
//            .antMatchers("/user/register").permitAll()
//            .antMatchers("/user/login").permitAll()
//            .antMatchers("/user/refreshToken").permitAll()
////            .antMatchers(HttpMethod.GET,"/user/testing").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic()
//            .and()
//            .exceptionHandling().authenticationEntryPoint(CustomAuthenticationEntryPoint())
////            .accessDeniedHandler(accessDeniedHandler())
//            .and().addFilterBefore(AuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        return http.build()
//    }

    fun securityFilterChain(http: HttpSecurity): HttpSecurity {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/user/register").permitAll()
            .antMatchers("/user/login").permitAll()
            .antMatchers("/user/refreshToken").permitAll()
            .antMatchers("/actuator/*").permitAll()
            .and()
            .httpBasic()
            .and()
            .exceptionHandling().authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .and().addFilterBefore(AuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http
    }

//    @Bean
//    fun accessDeniedHandler(): AccessDeniedHandler? {
//        return CustomAccessDeniedHandler()
//    }

}