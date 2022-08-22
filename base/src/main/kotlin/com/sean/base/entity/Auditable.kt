package com.sean.base.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import java.util.*
import javax.persistence.Basic
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditableListener::class)
abstract class Auditable<T> {

    @Basic
    @CreatedBy
    open var insertUser: String? = null

    @Basic
    @LastModifiedBy
    open var updateUser: String? = null

    @Basic
    open var insertDate: Date? = null

    @Basic
    open var updateDate: Date? = null

}