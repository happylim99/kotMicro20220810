package com.sean.auth_ws.controller

import com.sean.auth_ws.req.UserCrtReq
import com.sean.auth_ws.service.RoleService
import com.sean.auth_ws.service.UserService
import com.sean.common_dto.auth_ws.RoleCrtReq
import com.sean.common_dto.auth_ws.RoleDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/role")
class RoleController(
    private val srv: RoleService
) {

    @PostMapping("/createOne")
    fun createOne(@RequestBody req: RoleCrtReq): ResponseEntity<RoleDto> {
        return ResponseEntity.ok(srv.createOne(req))
    }

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

}