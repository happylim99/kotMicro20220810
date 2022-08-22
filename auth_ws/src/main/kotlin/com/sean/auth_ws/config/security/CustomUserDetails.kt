package com.sean.auth_ws.config.security

import com.sean.auth_ws.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

open class CustomUserDetails: UserDetails {

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var authority: MutableCollection<SimpleGrantedAuthority>

    constructor(
        username: String,
        password: String,
        authority: MutableCollection<SimpleGrantedAuthority>)
    {
        this.username = username
        this.password = password
        this.authority = authority
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.authority
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}