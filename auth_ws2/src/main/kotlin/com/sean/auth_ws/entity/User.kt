package com.sean.auth_ws.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sean.base.entity.Auditable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
open class User: Auditable<String> {

    constructor()

    constructor(username: String, email: String, passwd: String) {
        this.userName = username
        this.email = email
        this.passWord = passwd
    }

    constructor(
        id: Long,
        uid: String,
        name: String,
        userName: String,
        email: String,
        emailVerificationToken: String,
        emailVerificationStatus: Boolean,
        passWord: String,
        roles: MutableSet<Role>
    ) : super() {
        this.id = id
        this.uid = uid
        this.name = name
        this.userName = userName
        this.email = email
        this.emailVerificationToken = emailVerificationToken
        this.emailVerificationStatus = emailVerificationStatus
        this.passWord = passWord
        this.roles = roles
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0

    @Column(nullable = false, unique = true)
    open var uid = ""

    @Column(nullable = false, length = 50)
    open var name = ""

    @Column(nullable = false, length = 30)
    open var userName = ""

    @Column(nullable = false, length = 120, unique = true)
    open var email = ""

    @Column
    open var emailVerificationToken = ""

    @Column(nullable = false)
    open var emailVerificationStatus = false

    @Column(nullable = false)
    @JsonIgnore
    open var passWord = ""

    @Column
//    @JsonIgnoreProperties("users")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinTable(
        name = "role_user", joinColumns =
        [JoinColumn(name = "user_id", nullable = false, updatable = false)],
        inverseJoinColumns =
        [JoinColumn(name = "role_id", nullable = false, updatable = false)]
    )
    open var roles: MutableSet<Role>? = mutableSetOf()

}