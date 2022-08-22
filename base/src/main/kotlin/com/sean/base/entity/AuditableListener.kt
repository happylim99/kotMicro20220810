package com.sean.base.entity

import java.util.*
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class AuditableListener {

    @PrePersist
    fun prePersist(obj: Auditable<*>) {
        obj.insertDate = Date()
        getCurrentUser()?.let { obj.insertUser = it }
    }

    @PreUpdate
    fun preUpdate(obj: Auditable<*>) {
        obj.updateDate = Date()
        getCurrentUser()?.let { obj.updateUser = it }
    }

    private fun getCurrentUser(): String? {
//        val authentication = SecurityContextHolder.getContext().authentication
//        if(authentication == null || !authentication.isAuthenticated) {
//            return "auditable"
//        }
        return "auditable"
    }
}