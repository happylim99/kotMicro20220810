package com.sean.common_dto.auth_ws

data class RoleCrtReq(
    val roleName: String = ""
)

data class RoleUpdReq(
    val uid: String = "",
    val roleName: String = ""
)

data class RoleCriteriaReq(
    val roleName: String? = null
)
