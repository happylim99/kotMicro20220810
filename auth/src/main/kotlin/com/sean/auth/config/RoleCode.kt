package com.sean.auth.config

enum class RoleCode {
    ROLE_ROOT, ROLE_ADMIN, ROLE_SUPERUSER, ROLE_USER, ROLE_VIEWER;
    companion object {
        fun random(): Array<RoleCode> = RoleCode.values()
    }
}