package com.sean.auth_ws.service.impl

import com.sean.auth_ws.entity.Role
import com.sean.auth_ws.repo.RoleRepo
import com.sean.auth_ws.service.RoleService
import com.sean.base.annotation.Slf4j
import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.common_dto.auth_ws.RoleCrtReq
import com.sean.common_dto.auth_ws.RoleDto
import com.sean.common_dto.auth_ws.RoleUpdReq
import org.modelmapper.ModelMapper
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Slf4j
@Service
class RoleServiceImpl(
    private val repo: RoleRepo,
    private val modelMapper: ModelMapper
): RoleService {

    override fun findByRoleName() {
        TODO("Not yet implemented")
    }

    override fun createOne(req: RoleCrtReq): RoleDto {
        validateCrtRequireField(req)

        val rolename = req.roleName
        isRoleNameExist(rolename)

        var role = Role()
        var roleDto = RoleDto()
        BeanUtils.copyProperties(req, role)
        role.uid = getUUID()
        role = repo.save(role)
        BeanUtils.copyProperties(role, roleDto)
        return roleDto
    }

    override fun updateOne(uid: String, req: RoleUpdReq): RoleDto {
        TODO("Not yet implemented")
    }

    override fun showOne(uid: String): RoleDto? {
        TODO("Not yet implemented")
    }

    override fun showAll(): List<RoleDto>? {
        val roles = repo.findAll()
        var rtnValue: MutableList<RoleDto> = mutableListOf()
        roles.forEach {
            val roleDto = RoleDto()
            modelMapper.map(it, roleDto)
            rtnValue.add(roleDto)
        }
        return rtnValue
    }

    override fun deleteOne(uid: String): String {
        TODO("Not yet implemented")
    }

    private fun validateCrtRequireField(req: RoleCrtReq) {
        if(req.roleName.isNullOrEmpty()) {
            throw CException("Please provide roleName")
        }
    }

    private fun isRoleNameExist(req: String) {
        if(repo.findByRoleName(req) != null) {
            throw CException("$req already existed")
        }
    }
}