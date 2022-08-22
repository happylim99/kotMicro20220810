package com.sean.common_dto.auth_ws

data class UserDto(
    var uid: String = "",
    var name: String = "",
    var username: String = "",
    var email: String? = null,
    var roles: MutableList<RoleDto> = mutableListOf()
)

data class LoginDto(
    var email: String = "",
    var passwd: String = ""
)
