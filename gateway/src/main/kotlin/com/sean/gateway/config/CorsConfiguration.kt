package com.sean.gateway.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.HttpMethod
//import org.springframework.web.cors.reactive.CorsWebFilter
//import org.springframework.web.cors.reactive.DefaultCorsProcessor
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
//import org.springframework.web.server.ServerWebExchange
//import org.springframework.web.util.pattern.PathPatternParser
//
//
///**
// * 网关跨域配置
// */
//@Configuration
//class CorsConfiguration {
//    @Bean
//    fun corsResponseHeaderFilter(): CorsResponseHeaderFilter {
//        return CorsResponseHeaderFilter()
//    }
//
//    @Bean
//    fun corsFilter(): CorsWebFilter {
//        val source =
//            UrlBasedCorsConfigurationSource(PathPatternParser())
//        source.registerCorsConfiguration("/**", buildCorsConfiguration())
//        return CorsWebFilter(source, object : DefaultCorsProcessor() {
//            protected fun handleInternal(
//                exchange: ServerWebExchange?, config: CorsConfiguration?,
//                preFlightRequest: Boolean
//            ): Boolean {
//                // 预留扩展点
//                // if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
//                return super.handleInternal(exchange!!, config, preFlightRequest)
//                // }
//
//                // return true;
//            }
//        })
//    }
//
//    private fun buildCorsConfiguration(): CorsConfiguration {
//        val corsConfiguration = CorsConfiguration()
//        corsConfiguration.addAllowedOrigin("*")
//        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS)
//        corsConfiguration.addAllowedMethod(HttpMethod.POST)
//        corsConfiguration.addAllowedMethod(HttpMethod.GET)
//        corsConfiguration.addAllowedMethod(HttpMethod.PUT)
//        corsConfiguration.addAllowedMethod(HttpMethod.DELETE)
//        corsConfiguration.addAllowedMethod(HttpMethod.PATCH)
//        // corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedHeader("*")
//        corsConfiguration.setMaxAge(7200L)
//        corsConfiguration.setAllowCredentials(true)
//        return corsConfiguration
//    }
//}