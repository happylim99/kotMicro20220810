package com.sean.auth.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.base.util.SpringContext
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationEntryPoint(
    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java)
) : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        // 401
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json"
        response.let {
            val res = mutableMapOf<String, Any>()
            res["timestamp"] = Date()
            res["msg"] = "Authorization Fail"
            mapper.writeValue(response.outputStream, res)
        }
    }

}