package com.mufid.service.user;


import com.mufid.dto.register.RegisterRequest;
import com.mufid.dto.role.RoleDTO;
import com.mufid.dto.user.UserDTO;
import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.User;
import com.mufid.entity.bean.UserRole;
import com.mufid.repository.UserRepository;
import com.mufid.service.password.PasswordService;
import com.mufid.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(RegisterRequest request, Role role) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordService.encodePassword(request.getPassword()));
        user.setActive(true);
        user.setLocked(false);
        user.setLoginAttempt(0);
        user.setLogDate(new Date());

        user = userRepository.save(user);

        roleService.assignRoleToUser(user, role);
        List<UserRole> userRoles = user.getUserRoleList();
        return user;
    }

    @Override
    public void activateUser(User user) {
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void lockUser(User user) {
        user.setLocked(true);
        userRepository.save(user);
    }

    @Override
    public void unlockUser(User user) {
        user.setLocked(false);
        userRepository.save(user);
    }

    @Override
    public void incrementLoginAttempt(User user) {
        user.setLoginAttempt(user.getLoginAttempt() + 1);
        user.setLoginAttemptDate(new Date());
        userRepository.save(user);
    }

    @Override
    public void resetLoginAttempt(User user) {
        user.setLoginAttempt(0);
        user.setLoginAttemptDate(null);
        userRepository.save(user);
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setActive(user.getActive());

        // Convert roles
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (UserRole userRole : user.getUserRoleList()) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(userRole.getRole().getId());
            roleDTO.setName(userRole.getRole().getName());
            roleDTO.setType(userRole.getRole().getType());
            roleDTOs.add(roleDTO);
        }
        dto.setRoles(roleDTOs);

        return dto;
    }
}
