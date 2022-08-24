package com.sean.gateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Configuration

//@Configuration
//class RouteConfig {
//
//    @Bean
//    fun routeLocator(builder: RouteLocatorBuilder) = builder.routes {
//        route() {
//            path("/**")
//            filters {
//                setResponseHeader("Access-Control-Allow-Origin", "*")
////                rewritePath("/auth-ws/*", "/aa/*")
//            }
////            uri("lb:http://localhost:auth-ws")
//            uri("http://localhost:4200")
//        }
//    }
//}