package com.sean.auth.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.config.AuthConst
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthUtil(
    private val mapper: ObjectMapper,
    private val env: Environment
) {

    fun decodeJwt(request: HttpServletRequest, response: HttpServletResponse): DecodedJWT {
        val authHeader = request.getHeader(AuthConst.HEADER_STRING)
        val token = authHeader.replace(AuthConst.TOKEN_PREFIX, "")
        val jwtVerifier = JWT.require(algorithm()).build()
        return jwtVerifier.verify(token)
    }

    fun authSuccessRes(response: HttpServletResponse?, accessToken: String, refreshToken: String) {
        response?.let {
            val res = authSuccessBody(accessToken, refreshToken)
            mapper.writeValue(response.outputStream, res)
        }
    }

    fun authSuccessBody(accessToken: String, refreshToken: String): MutableMap<String, String> {
        return mutableMapOf(
            "access_token" to accessToken,
            "refresh_token" to refreshToken
        )
    }

    fun authFailRes(response: HttpServletResponse, e: Exception) {
        response.setHeader("error", e.message)
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json"
        response.let {
            val res = mutableMapOf<String, Any>()
            res["Timestamp"] = Date()
            res["Msg"] = e.message.toString()
            mapper.writeValue(response.outputStream, res)
        }
    }

    fun algorithm(): Algorithm = Algorithm.HMAC512(env.getProperty("secret.token"))

}