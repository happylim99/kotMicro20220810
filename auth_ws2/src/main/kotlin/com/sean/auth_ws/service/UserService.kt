package com.sean.auth_ws.service

import com.sean.auth_ws.entity.User
import com.sean.auth_ws.req.*
import com.sean.base.service.Crud
import com.sean.common_dto.auth_ws.*
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface UserService: UserDetailsService, Crud<UserDto, UserCrtReq, UserUpdReq> {

    fun logIn(req: LoginDto, httpServletRequest: HttpServletRequest): ResponseEntity<Any>
    fun showPag(page: Int, limit: Int): List<UserDto>
    fun userWantRole(req: UserWantRoleReq): UserDto
    fun findByEmail(email: String): User?
    fun findByEmailReturnUserDto(email: String): UserDto?
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse)
    fun viewUserDetails()
}