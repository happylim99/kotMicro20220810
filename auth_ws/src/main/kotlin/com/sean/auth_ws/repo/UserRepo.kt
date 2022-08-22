package com.sean.auth_ws.repo

import com.sean.auth_ws.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface UserRepo: JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun findByUid(uid: String): User?

    fun deleteByUid(uid: String): Long
}