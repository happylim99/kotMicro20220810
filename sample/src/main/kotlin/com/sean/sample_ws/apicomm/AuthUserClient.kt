package com.sean.sample_ws.apicomm

import com.sean.common_dto.auth_ws.UserDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

//@FeignClient(name = "user-ws", fallback = UserServiceClientImpl::class)
@FeignClient(name = "auth-ws")
interface AuthUserClient {

    @GetMapping("/test/{uid}")
    fun getUserDto(@PathVariable uid: String): String
}