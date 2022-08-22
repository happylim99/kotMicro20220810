package com.sean.auth_ws.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth_ws.util.AuthWsUtil
import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import com.sean.base.util.SpringContext
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//@Slf4j
//class AuthorizationFilter(
//    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java),
//    private val authWsUtil: AuthWsUtil = SpringContext.getBean(AuthWsUtil::class.java)
//): OncePerRequestFilter() {
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//
//        if(request.servletPath.equals("/user/login")
//            || request.servletPath.equals("/user/register")
//            || request.servletPath.equals("/user/refreshToken")) {
//            filterChain.doFilter(request, response)
//        } else {
//            val authHeader: String? = request.getHeader(SecurityConst.HEADER_STRING)
//            if(authHeader != null && authHeader.startsWith(SecurityConst.TOKEN_PREFIX)) {
//                try {
//                    val decodeJwt = authWsUtil.decodeJwt(request, response)
//                    val username = decodeJwt.subject
//                    val rolesStr = decodeJwt.claims["roles"]
//                        .toString().drop(2).dropLast(2).replace(" ", "")
//                    val roles = rolesStr.split(",")
////                    val userStr = decodeJwt.getClaim("user").asString()
////                    val user = mapper.readValue(userStr, com.sean.auth_ws.entity.User::class.java)
//
//                    val authorities = roles.map {
//                        SimpleGrantedAuthority(it)
//                    }?.toList()
//                    val authToken = UsernamePasswordAuthenticationToken(username, null, authorities)
//                    SecurityContextHolder.getContext().authentication = authToken
//                    filterChain.doFilter(request, response)
//                } catch (e: Exception) {
//                    log.error("AuthorizationFilter error $e")
//                    authWsUtil.authFailRes(response, e)
////                    response.sendError(FORBIDDEN.absoluteValue)
//                }
//
//            } else {
//                filterChain.doFilter(request, response)
//            }
//        }
//    }
//}