package com.sean.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer


//@Configuration
//@EnableWebFlux
//class CORSFilter : WebFluxConfigurer {
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowCredentials(true)
//            .allowedOrigins("*")
//            .allowedHeaders("*")
//            .allowedMethods("*")
//    }
//
//    @Bean
//    fun corsWebFilter(): CorsWebFilter {
//        val corsConfiguration = CorsConfiguration()
//        corsConfiguration.allowCredentials = true
//        corsConfiguration.addAllowedHeader("*")
//        corsConfiguration.addAllowedMethod("*")
//        corsConfiguration.addAllowedOrigin("*")
//        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
//        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)
//        return CorsWebFilter(corsConfigurationSource)
//    }
//}

@Configuration
class CorsFilter {

//    @Bean
//    fun corsWebFilter(): CorsWebFilter {
//        val corsConfiguration = CorsConfiguration()
////        corsConfiguration.allowCredentials = true
//        corsConfiguration.applyPermitDefaultValues()
//        corsConfiguration.addAllowedHeader("*")
//        corsConfiguration.addAllowedMethod("*")
//        corsConfiguration.addAllowedOrigin("*")
//        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
//        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)
//        return CorsWebFilter(corsConfigurationSource)
//    }

}