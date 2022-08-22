package com.sean.auth_ws.config.security

import com.sean.auth_ws.entity.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.access.vote.RoleHierarchyVoter
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

//@Configuration
//class GlobalMethodSecurityConfig: GlobalMethodSecurityConfiguration() {
//
//    override fun createExpressionHandler(): MethodSecurityExpressionHandler? {
//        return methodSecurityExpressionHandler()
//    }
//
//    private fun methodSecurityExpressionHandler(): DefaultMethodSecurityExpressionHandler? {
//        val expressionHandler = DefaultMethodSecurityExpressionHandler()
//        expressionHandler.setRoleHierarchy(roleHierarchy())
//        return expressionHandler
//    }
//
////    @Bean
//    fun roleHierarchy(): RoleHierarchyImpl {
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
////    @Bean
//    fun roleVoter(): RoleHierarchyVoter? {
//        return RoleHierarchyVoter(roleHierarchy())
//    }
//
//}