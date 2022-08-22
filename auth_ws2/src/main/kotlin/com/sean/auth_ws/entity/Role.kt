package com.sean.auth_ws.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sean.auth.config.RoleCode
import javax.persistence.*

@Entity
class Role {

    constructor()

    constructor(uid: String, roleName: String) {
        this.uid = uid
        this.roleName = roleName
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false, unique = true)
    var uid = ""

    @Column(unique = true)
    var roleName: String = RoleCode.ROLE_VIEWER.name

    @Column
//    @JsonIgnoreProperties("roles")
    @JsonBackReference
//    @JsonManagedReference
    @ManyToMany(mappedBy = "roles", cascade = [CascadeType.DETACH])
    var users: MutableSet<User> = mutableSetOf()

    override fun toString(): String {
        return "Role(id=$id, roleName='$roleName', users=$users)"
    }
}