package com.sean.auth

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sean"])
//@EnableAutoConfiguration(exclude=[RabbitAutoConfiguration::class])
class AuthApplication

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}
