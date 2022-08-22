package com.sean.base.bean

import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BaseBeanConfig {

    @Bean
    fun mapper() = ObjectMapper()

    @Bean
    fun modelMapper() = ModelMapper()
}