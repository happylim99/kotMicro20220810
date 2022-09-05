package com.sean.webclient.dto;

public class RoleDto {
    private String uid;
    private String roleName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleDto(String uid, String roleName) {
        this.uid = uid;
        this.roleName = roleName;
    }

    public RoleDto() {
    }
}
