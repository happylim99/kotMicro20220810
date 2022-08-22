package com.sean.auth_ws.service

import com.sean.base.service.Crud
import com.sean.common_dto.auth_ws.RoleCrtReq
import com.sean.common_dto.auth_ws.RoleDto
import com.sean.common_dto.auth_ws.RoleUpdReq

interface RoleService: Crud<RoleDto, RoleCrtReq, RoleUpdReq> {

    fun findByRoleName()

}