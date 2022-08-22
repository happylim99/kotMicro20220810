package com.sean.auth.config

import com.sean.auth.util.AuthUtil
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

@Slf4j
class AuthorizationFilter(
    private val authUtil: AuthUtil = SpringContext.getBean(AuthUtil::class.java)
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        if(request.servletPath.equals("/user/login")
            || request.servletPath.equals("/user/register")
            || request.servletPath.equals("/user/refreshToken")) {
            filterChain.doFilter(request, response)
        } else {
            val authHeader: String? = request.getHeader(AuthConst.HEADER_STRING)
            if(authHeader != null && authHeader.startsWith(AuthConst.TOKEN_PREFIX)) {
                try {
                    val decodeJwt = authUtil.decodeJwt(request, response)
                    val username = decodeJwt.subject
                    val rolesStr = decodeJwt.claims["roles"]
                        .toString().drop(2).dropLast(2).replace(" ", "")
                    val roles = rolesStr.split(",")
//                    val userStr = decodeJwt.getClaim("user").asString()
//                    val user = mapper.readValue(userStr, com.sean.auth_ws.entity.User::class.java)

                    val authorities = roles.map {
                        SimpleGrantedAuthority(it)
                    }?.toList()
                    val authToken = UsernamePasswordAuthenticationToken(username, null, authorities)
                    SecurityContextHolder.getContext().authentication = authToken
                    filterChain.doFilter(request, response)
                } catch (e: Exception) {
                    log.error("AuthorizationFilter error $e")
                    authUtil.authFailRes(response, e)
                }

            } else {
                filterChain.doFilter(request, response)
            }
        }
    }
}