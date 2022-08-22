package com.sean.auth_ws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication(scanBasePackages= ["com.sean"])
@EnableEurekaClient
class AuthWs2Application

fun main(args: Array<String>) {
	runApplication<AuthWs2Application>(*args)
}
