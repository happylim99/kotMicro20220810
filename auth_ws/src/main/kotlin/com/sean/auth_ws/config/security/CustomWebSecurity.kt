package com.sean.auth_ws.config.security

import com.sean.auth.config.AuthorizationFilter
import com.sean.auth.config.CustomAccessDeniedHandler
import com.sean.auth.config.CustomAuthenticationEntryPoint
import com.sean.auth.config.WebSecurity
import com.sean.auth_ws.service.UserService
import org.springframework.beans.factory.annotation.Lookup
import org.springframework.context.annotation.*
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
class CustomWebSecurity: WebSecurity() {

//    @Bean
//    @Throws(Exception::class)
//    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
//        return authenticationConfiguration.authenticationManager
//    }

//    @Bean
//    @Primary
//    fun configure(http: HttpSecurity): SecurityFilterChain {
////        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
////        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bcrypt)
//
////        val expressionHandler = DefaultWebSecurityExpressionHandler()
////        expressionHandler.setRoleHierarchy(roleHierarchy())
//
//    println("CustomWebSecurity configure")
//        http.csrf().disable()
//
//            .authorizeRequests()
////            .accessDecisionManager(accessDecisionManager())
////            .expressionHandler(webSecurityExpressionHandler())
//            .antMatchers("/user/register").permitAll()
//            .antMatchers("/user/login").permitAll()
//            .antMatchers(HttpMethod.GET, "/user/testing").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic()
//            .and()
//            .exceptionHandling().authenticationEntryPoint(CustomAuthenticationEntryPoint())
////            .accessDeniedHandler(accessDeniedHandler())
//            .and().addFilterBefore(AuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        return http.build()
//    }

    @Bean
    override fun configure(http: HttpSecurity): SecurityFilterChain {
        super.securityFilterChain(http)
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,"/user/testing").permitAll()
            .anyRequest().authenticated()
        return http.build()
    }

//    @Bean
//    fun accessDeniedHandler(): AccessDeniedHandler? {
//        return CustomAccessDeniedHandler()
//    }

//    @Bean
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
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_SUPERUSER\nROLE_SUPERUSER > ROLE_USER")
//        return roleHierarchy
//    }

//    @Bean
//    fun webSecurityExpressionHandler(): DefaultWebSecurityExpressionHandler {
//        val expressionHandler = DefaultWebSecurityExpressionHandler()
//        expressionHandler.setRoleHierarchy(roleHierarchy())
//        return expressionHandler
//    }

//    @Bean
//    fun hierarchyVoter(): AccessDecisionVoter<*>? {
//        var hierarchy = RoleHierarchyImpl()
//        hierarchy.setHierarchy(
//            """
//            ROLE_ADMIN > ROLE_SUPERUSER
//            ROLE_SUPERUSER > ROLE_USER
//            """.trimIndent()
//        )
//        return RoleHierarchyVoter(hierarchy)
//    }

//    @Bean
//    fun roleVoter(): RoleHierarchyVoter {
//        return RoleHierarchyVoter(roleHierarchy())
//    }
//
//    @Bean
//    fun expressionHandler(): DefaultWebSecurityExpressionHandler? {
//        val expressionHandler = DefaultWebSecurityExpressionHandler()
//        expressionHandler.setRoleHierarchy(roleHierarchy())
//        return expressionHandler
//    }
//
//    @Bean
//    fun accessDecisionManager(): AffirmativeBased? {
//        val decisionVoters: MutableList<AccessDecisionVoter<*>> = ArrayList()
//        val webExpressionVoter = WebExpressionVoter()
//        webExpressionVoter.setExpressionHandler(expressionHandler())
//        decisionVoters.add(webExpressionVoter)
//        decisionVoters.add(roleVoter())
//        return AffirmativeBased(decisionVoters)
//    }

    //    private fun webSecurityExpressionHandler(): SecurityExpressionHandler<FilterInvocation?>? {
//        val defaultWebSecurityExpressionHandler = DefaultWebSecurityExpressionHandler()
//        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy())
//        return defaultWebSecurityExpressionHandler
//    }

}