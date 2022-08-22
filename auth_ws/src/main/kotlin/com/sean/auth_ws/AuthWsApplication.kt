package com.sean.auth_ws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages= ["com.sean"])
@EnableEurekaClient
@EnableFeignClients
class AuthWsApplication

fun main(args: Array<String>) {
	runApplication<AuthWsApplication>(*args)
}
