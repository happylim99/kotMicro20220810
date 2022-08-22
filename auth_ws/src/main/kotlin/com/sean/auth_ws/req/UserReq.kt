package com.sean.auth_ws.req

data class UserCrtReq(
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var passwd: String = "",
    var confirmPasswd: String = ""
)

data class UserUpdReq(
    var name: String = "",
    var username: String = ""
)

data class PasswdChangeReq(
    var passwd: String = "",
    var confirmPasswd: String = ""
)

data class UserWantRoleReq(
    var userUid: String = "",
    var roleList: List<String> = listOf()
)