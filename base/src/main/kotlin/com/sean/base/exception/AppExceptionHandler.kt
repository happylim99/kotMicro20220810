package com.sean.base.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class AppExceptionHandler {

    @ExceptionHandler(value = [CException::class])
    fun handleCException(ex: CException, request: WebRequest?): ResponseEntity<Any?>? {
        val errorMessage = ErrorMsg(msg = ex.message!!)
        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(ex: Exception, request: WebRequest?): ResponseEntity<Any?>? {
        var status = HttpStatus.INTERNAL_SERVER_ERROR
        if(ex.message == "Access is denied"){
            status = HttpStatus.FORBIDDEN
        }
        val errorMessage = ErrorMsg(msg = ex.message!!)
        return ResponseEntity(errorMessage, HttpHeaders(), status)
    }

}