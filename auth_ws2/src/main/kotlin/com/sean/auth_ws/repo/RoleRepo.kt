package com.sean.auth_ws.repo

import com.sean.auth_ws.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepo: JpaRepository<Role, Long> {

    fun findByRoleName(name: String): Role?

}