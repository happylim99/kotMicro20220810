package com.sean.sample_ws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication(scanBasePackages= ["com.sean"])
@EnableEurekaClient
class SampleApplication

fun main(args: Array<String>) {
	runApplication<SampleApplication>(*args)
}
