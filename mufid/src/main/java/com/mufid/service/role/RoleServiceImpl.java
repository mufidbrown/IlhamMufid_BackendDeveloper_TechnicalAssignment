package com.mufid.service.role;

import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.User;
import com.mufid.entity.bean.UserRole;
import com.mufid.repository.RoleRepository;
import com.mufid.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> findDefaultRoles() {
        // Assuming default roles have type = 1
        return roleRepository.findByType(1);
    }

    @Override
    public UserRole assignRoleToUser(User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setStartDate(new Date());

        return userRoleRepository.save(userRole);
    }
}
