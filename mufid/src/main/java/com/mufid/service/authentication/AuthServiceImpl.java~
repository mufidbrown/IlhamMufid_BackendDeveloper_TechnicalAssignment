package com.mufid.service.authentication;


import com.mufid.dto.authentication.AuthResponse;
import com.mufid.dto.login.LoginRequest;
import com.mufid.dto.register.RegisterRequest;
import com.mufid.dto.user.UserDTO;
import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.Token;
import com.mufid.entity.bean.User;
import com.mufid.exception.AuthException;
import com.mufid.repository.PasswordResetTokenRepository;
import com.mufid.service.password.PasswordService;
import com.mufid.service.role.RoleService;
import com.mufid.service.token.TokenService;
import com.mufid.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private static final int MAX_LOGIN_ATTEMPTS = 5;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public AuthResponse register(RegisterRequest request) throws AuthException {
        // Validate request
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AuthException("Passwords do not match");
        }

        // Check if user with email already exists
        if (userService.findByEmail(request.getEmail()) != null) {
            throw new AuthException("Email is already registered");
        }

        try {
            // Get role
            Role role;
            if (request.getRoleId() != null) {
                role = roleService.findById(request.getRoleId());
                if (role == null) {
                    throw new AuthException("Invalid role selected");
                }
            } else {
                // Get default role if not specified
                List<Role> defaultRoles = roleService.findDefaultRoles();
                if (defaultRoles.isEmpty()) {
                    throw new AuthException("No default role available");
                }
                role = defaultRoles.get(0);
            }

            // Create user
            User user = userService.createUser(request, role);


            return new AuthResponse(true, "Registration successful. Please check your email for confirmation.", null, null);

        } catch (Exception e) {
            logger.error("Registration failed", e);
            throw new AuthException("Registration failed: " + e.getMessage(), e);
        }
    }


    @Override
    public AuthResponse login(LoginRequest request) throws AuthException {
        User user = userService.findByEmail(request.getEmail());

        if (user == null) {
            throw new AuthException("Invalid email or password");
        }

        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new AuthException("Your account is locked. Please contact support or reset your password.");
        }

        if (Boolean.FALSE.equals(user.getActive())) {
            throw new AuthException("Your account is not active. Please check your email to activate your account.");
        }

        if (!passwordService.matches(request.getPassword(), user.getPassword())) {
            userService.incrementLoginAttempt(user);

            if (user.getLoginAttempt() >= MAX_LOGIN_ATTEMPTS) {
                userService.lockUser(user);
                throw new AuthException("Account locked due to too many failed login attempts. Please reset your password.");
            }

            throw new AuthException("Invalid email or password");
        }

        userService.resetLoginAttempt(user);

        // ✅ Tambahkan validasi role di sini:
        if (user.getUserRoleList() == null || user.getUserRoleList().isEmpty()) {
            throw new AuthException("User does not have any roles assigned.");
        }

        // Baru ambil role setelah aman
        Role role = user.getUserRoleList().get(0).getRole();

        Token token = tokenService.createToken(user, role);
        UserDTO userDTO = userService.convertToDTO(user);

        return new AuthResponse(true, "Login successful", token.getToken(), userDTO);
    }

}


