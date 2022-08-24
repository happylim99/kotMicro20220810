package com.sean.gateway.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.core.Ordered
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


//@Bean
//fun simpleCorsFilter(): FilterRegistrationBean<Filter>? {
//    val source = UrlBasedCorsConfigurationSource()
//    val config = CorsConfiguration()
//    config.allowCredentials = true
//    config.allowedOrigins = listOf("*")
//    config.allowedMethods = listOf("*")
//    config.allowedHeaders = listOf("*")
//    source.registerCorsConfiguration("/**", config)
//    val bean = FilterRegistrationBean(CorsFilter(source))
//    bean.setOrder(Ordered.HIGHEST_PRECEDENCE)
//    return bean
//}