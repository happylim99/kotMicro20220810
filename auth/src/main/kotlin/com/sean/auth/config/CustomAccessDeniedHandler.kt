package com.sean.auth.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.base.util.SpringContext
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler(
    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java)
) : AccessDeniedHandler {

    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/json"
        response.let {
            val res = mutableMapOf<String, Any>()
            res["timestamp"] = Date()
            res["msg"] = "${accessDeniedException?.message}"
            mapper.writeValue(response.outputStream, res)
        }
    }
}