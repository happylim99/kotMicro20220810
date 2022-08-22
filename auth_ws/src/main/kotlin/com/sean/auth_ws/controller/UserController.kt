package com.sean.auth_ws.controller

import com.sean.auth_ws.req.*
import com.sean.auth_ws.service.UserService
import com.sean.common_dto.auth_ws.LoginDto
import com.sean.common_dto.auth_ws.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/user")
class UserController(
    private val srv: UserService
) {

    @PostMapping("/register")
    fun createOne(@RequestBody userReq: UserCrtReq): ResponseEntity<UserDto> {
        return ResponseEntity.ok(srv.createOne(userReq))
    }

//    @PostMapping("/login")
//    fun login(@RequestBody req: LoginDto, httpServletRequest: HttpServletRequest)
//    : ResponseEntity<Any> {
//        return try {
//            val authentication: Authentication = authenticationManager.authenticate(
//                UsernamePasswordAuthenticationToken(
//                    req.email, req.passwd
//                )
//            )
//            println(authentication.principal)
//            val user: CustomUserDetails = authentication.principal as CustomUserDetails
//            println(user.username)
//            val accessToken: String = authUtil.genAccessToken(user, httpServletRequest.requestURL.toString())
//            val response = AuthRes(user.username, accessToken)
//            ResponseEntity.ok().body<Any>(response)
//        } catch (ex: BadCredentialsException) {
//            var resBody = mutableMapOf(
//                "timestamp" to Date(),
//                "msg" to "Please provide a correct username and password")
//            ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body<Any>(resBody)
//        }
//    }

    @PostMapping("/login")
    fun login(@RequestBody req: LoginDto, httpServletRequest: HttpServletRequest)
            : ResponseEntity<Any> {
        return srv.logIn(req, httpServletRequest)
    }

    @PutMapping("/{uid}")
    fun updateOne(@PathVariable uid: String,
                  @RequestBody userReq: UserUpdReq
    ): ResponseEntity<UserDto> {
        return ResponseEntity.ok(srv.updateOne(uid, userReq))
    }

    @GetMapping("/{uid}")
    fun showOne(@PathVariable uid: String) = ResponseEntity.ok(srv.showOne(uid))

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: String) = ResponseEntity.ok(srv.deleteOne(id))

    @GetMapping("/showPag")
    fun showPag(@RequestParam(value = "page", defaultValue = "1") page: Int,
                @RequestParam(value = "limit", defaultValue = "5") limit:Int
    ): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(srv.showPag(page, limit))
    }

    @GetMapping("/refreshToken")
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        srv.refreshToken(request, response)
    }

    @PostMapping("/userWantRole")
    fun userWantRole(@RequestBody req: UserWantRoleReq)
            : ResponseEntity<Any> {
        return ResponseEntity.ok(srv.userWantRole(req))
    }

    @GetMapping("/viewUserDetails")
    fun viewUserDetails(request: HttpServletRequest, response: HttpServletResponse) {
        srv.viewUserDetails()
    }

    @GetMapping("/testing")
    fun testing(): ResponseEntity<String> {
        return ResponseEntity.ok("aaaaaaaaaaaaaaaaaaaaaaaaa")
    }

    @GetMapping("/test/{uid}")
    fun testOne(@PathVariable uid: String) = ResponseEntity.ok("hahaha $uid")

}