package com.mufid.dto.user;


import com.mufid.dto.role.RoleDTO;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
    private Integer id;
    private String fullName;
    private String email;
    private Boolean isActive;
    private List<RoleDTO> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
