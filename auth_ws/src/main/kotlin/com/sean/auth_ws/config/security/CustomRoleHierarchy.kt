package com.sean.auth_ws.config.security

import com.sean.auth_ws.entity.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl

//@Configuration
//class CustomRoleHierarchy {
//
//    @Bean
//    fun roleHierarchy(): RoleHierarchy {
//        val roleHierarchy = RoleHierarchyImpl()
//        var hierarchy = StringBuilder()
//        Role.RoleCode.random().forEachIndexed { index, roleCode ->
//            if(index == 0) {
//                hierarchy.append("$roleCode > ${Role.RoleCode.random()[index+1]}")
//            } else if(index != Role.RoleCode.random().lastIndex) {
//                hierarchy.append("\n$roleCode > ${Role.RoleCode.random()[index+1]}")
//            }
//        }
//        roleHierarchy.setHierarchy(hierarchy.toString())
//        return roleHierarchy
//    }
//
//}