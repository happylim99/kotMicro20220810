package com.sean.auth_ws.service.impl

import com.sean.auth_ws.annotation.*
import com.sean.auth_ws.config.security.CustomUserDetails
import com.sean.auth_ws.config.security.SecurityConst
import com.sean.auth_ws.entity.User
import com.sean.auth_ws.repo.RoleRepo
import com.sean.auth_ws.repo.UserRepo
import com.sean.auth_ws.req.*
import com.sean.auth_ws.service.UserService
import com.sean.auth_ws.util.AuthWsUtil
import com.sean.auth_ws.util.ErrorMsgList
import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.common_dto.auth_ws.LoginDto
import com.sean.common_dto.auth_ws.UserDto
import org.modelmapper.ModelMapper
import org.springframework.beans.BeanUtils
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Service
class UserServiceImpl(
    private val repo: UserRepo,
    private val rRepo: RoleRepo,
    private val bcrypt: BCryptPasswordEncoder,
    private val modelMapper: ModelMapper,
    private val authUtil: AuthWsUtil,
    @Lazy private val authenticationManager: AuthenticationManager
): UserService {

    @Level4User
    override fun createOne(req: UserCrtReq): UserDto {
        validateCrtRequireField(req)

        if(validatePassword(req))
            throw CException(ErrorMsgList.PASSWORD_MISMATCH.value)

        if(repo.findByEmail(req.email) != null)
            throw CException(ErrorMsgList.ACCOUNT_EXIST.value)

        var user = User()
        BeanUtils.copyProperties(req, user)
        user.uid = getUUID()
        user.passWord = bcrypt.encode(req.passwd)
        user.emailVerificationToken = authUtil.genEmailToken()
        user = repo.save(user)
        var userDto = UserDto()
        BeanUtils.copyProperties(user, userDto)
        return userDto
    }

    override fun updateOne(uid: String, req: UserUpdReq): UserDto {
        var user: User = repo.findByUid(uid) ?: throw CException("Not Found")
        var userDto = UserDto()
        user.apply {
            name = req.name
            userName = req.username
            repo.save(this)
            BeanUtils.copyProperties(user, userDto)
        }
        return userDto
    }

    @Level5Viewer
    override fun showOne(uid: String): UserDto? {
        val user: User = repo.findByUid(uid) ?: throw CException("Not Found")
        var userDto = UserDto()
        user?.let { BeanUtils.copyProperties(user, userDto) }
        return userDto
    }

//    @Secured("ROLE_USER")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @Level5Viewer
    override fun showAll(): List<UserDto> {
        val users = repo.findAll()
        var rtnValue: MutableList<UserDto> = mutableListOf()
        users.forEach {
            val userDto = UserDto()
            modelMapper.map(it, userDto)
            rtnValue.add(userDto)
        }
        return rtnValue
    }

    override fun logIn(req: LoginDto, httpServletRequest: HttpServletRequest): ResponseEntity<Any> {
        return try {
            val user1 = req.email?.let{
                repo.findByEmail(req.email)}
                ?: throw UsernameNotFoundException("$req.email")
//            val authorities:MutableCollection<SimpleGrantedAuthority> = mutableListOf()
//            user1?.roles?.forEach { role ->
//                authorities.add(SimpleGrantedAuthority(role.roleName))
//            }
            val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    req.email, req.passwd
                )
            )
            val user: CustomUserDetails = authentication.principal as CustomUserDetails
            val accessToken: String = authUtil.genAccessToken(user, httpServletRequest.requestURL.toString())
            ResponseEntity.ok().body<Any>(authUtil.authSuccessBody(accessToken, accessToken))
        } catch (ex: BadCredentialsException) {
            var resBody = mutableMapOf(
                "timestamp" to Date(),
                "msg" to "Please provide a correct username and password")
            ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body<Any>(resBody)
        }
    }

    override fun showPag(page: Int, limit: Int): MutableList<UserDto> {
        val pagReq = PageRequest.of(page, limit)
        val userPage = repo.findAll(pagReq)
        var rtnValue: MutableList<UserDto> = mutableListOf()
        userPage.content.forEach {
            var userRes = UserDto()
            BeanUtils.copyProperties(it, userRes)
            rtnValue.add(userRes)
        }
        return rtnValue
    }

    override fun deleteOne(uid: String): String {
        val theId = repo.deleteByUid(uid)
        return if(theId.equals(0)) "Unable to delete $uid" else "$uid deleted"
    }

    override fun userWantRole(req: UserWantRoleReq): UserDto {
        var user = repo.findByUid(req.userUid) ?: throw CException("User not found")
        req.roleList.forEach {
            val role = rRepo.findByRoleName(it) ?: throw CException("Role $it not found")
            user.roles?.add(role)
        }
        user = repo.save(user)
        var userDto = UserDto()
        user?.let { BeanUtils.copyProperties(user, userDto) }
        return userDto
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        println("loadUserByUsername")
        val user = email?.let{
            repo.findByEmail(email)}
            ?: throw UsernameNotFoundException("$email")

        val authorities:MutableCollection<SimpleGrantedAuthority> = mutableListOf()
        user?.roles?.forEach { role ->
            authorities.add(SimpleGrantedAuthority(role.roleName))
        }

        return CustomUserDetails(user?.email, user?.passWord, authorities)
    }

    override fun findByEmail(email: String) = repo.findByEmail(email)

    override fun findByEmailReturnUserDto(email: String): UserDto? {
        val user = repo.findByEmail(email)
        var userRes = UserDto()
        user?.let { BeanUtils.copyProperties(it, userRes) }
        return userRes
    }

    override fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        val authHeader = request.getHeader(SecurityConst.HEADER_STRING)
        if((authHeader != null) && authHeader.startsWith(SecurityConst.TOKEN_PREFIX)) {
            try {

                val decodeJwt = authUtil.decodeJwt(request, response)
                val user: CustomUserDetails = findByEmail(decodeJwt.subject) as CustomUserDetails
                    ?: throw CException("not found")

                val accessToken = authUtil.genAccessToken(user,
                    request?.requestURL.toString()
                )

                val refreshToken = accessToken
//                val refreshToken = authUtil.genRefreshToken(user?.username,
//                    request?.requestURL.toString(), user?.let { it.roles?.map { it.roleName }?.toList() },
//                    user
//                )
                response?.addHeader(SecurityConst.HEADER_STRING, SecurityConst.TOKEN_PREFIX + accessToken)
                response?.contentType = "application/json"

                authUtil.authSuccessRes(response, accessToken, refreshToken)

            } catch (e: Exception) {
                log.error("AuthorizationFilter error $e")
                authUtil.authFailRes(response, e)
            }

        } else {
            throw Exception("Missing refresh token")
        }
    }

    override fun viewUserDetails() {
        val authentication = SecurityContextHolder.getContext().authentication
//        val user: CustomUserDetails = authentication.principal as CustomUserDetails
        println(authentication)
        println(authentication.principal)
        println(authentication.authorities)
    }

    private fun validateCrtRequireField(req: UserCrtReq) {
        if(req.name.isNullOrEmpty()) {
            throw CException("Please enter name")
        } else if(req.username.isNullOrEmpty()) {
            throw CException("Please enter username")
        } else if(req.email.isNullOrEmpty()) {
            throw CException("Please enter email")
        } else if(req.passwd.isNullOrEmpty()) {
            throw CException("Please enter password")
        } else if(req.confirmPasswd.isNullOrEmpty()) {
            throw CException("Please enter confirm password")
        }
    }

    private fun validatePassword(req: UserCrtReq): Boolean {
        if(req.passwd != req.confirmPasswd) {
            return true
        }
        return false
    }
}