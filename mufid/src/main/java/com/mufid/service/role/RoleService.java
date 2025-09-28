package com.mufid.service.role;

import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.User;
import com.mufid.entity.bean.UserRole;

import java.util.List;

public interface RoleService {
    Role findById(Integer id);
    List<Role> findDefaultRoles();
    UserRole assignRoleToUser(User user, Role role);
}
