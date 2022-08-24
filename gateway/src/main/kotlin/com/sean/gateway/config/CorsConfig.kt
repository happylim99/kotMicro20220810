package com.sean.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

//@Configuration
//class CorsConfig {
//    @Bean
//    fun corsFilter(): CorsWebFilter {
//        return CorsWebFilter(corsConfigurationSource())
//    }
//
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration().applyPermitDefaultValues()
//        config.addAllowedMethod(HttpMethod.GET)
//        config.addAllowedMethod(HttpMethod.POST)
//        config.addAllowedMethod(HttpMethod.PUT)
//        config.addAllowedMethod(HttpMethod.DELETE)
//        config.addAllowedMethod(HttpMethod.OPTIONS)
//        source.registerCorsConfiguration("/**", config)
//        return source
//    }
//}

//@Configuration
//class CorsConfig : CorsConfiguration() {
//    @Bean
//    fun corsFilter(): CorsWebFilter {
//        val corsConfiguration = CorsConfiguration()
//        corsConfiguration.allowCredentials = true
//        corsConfiguration.addAllowedOrigin("*")
//        corsConfiguration.allowedMethods = mutableListOf("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
//        corsConfiguration.addAllowedHeader("origin")
//        corsConfiguration.addAllowedHeader("content-type")
//        corsConfiguration.addAllowedHeader("accept")
//        corsConfiguration.addAllowedHeader("authorization")
//        corsConfiguration.addAllowedHeader("cookie")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", corsConfiguration)
//        return CorsWebFilter(source)
//    }
//}