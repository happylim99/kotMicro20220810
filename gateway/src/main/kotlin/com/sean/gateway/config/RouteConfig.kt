package com.sean.gateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Configuration

//@Configuration
class RouteConfig {

//    @Bean
//    fun routeLocator(builder: RouteLocatorBuilder) = builder.routes {
//        route("/auth-ws") {
//            path("/auth-ws/**")
////            filters {
////                rewritePath("/auth-ws/*", "/aa/*")
////            }
//            uri("lb:http://localhost:auth-ws")
//        }
//    }
}