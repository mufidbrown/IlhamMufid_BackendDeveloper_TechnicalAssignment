package com.mufid.service.authentication;


import com.mufid.dto.authentication.AuthResponse;
import com.mufid.dto.forgotpassword.ForgotPasswordRequest;
import com.mufid.dto.login.LoginRequest;
import com.mufid.dto.register.RegisterRequest;
import com.mufid.dto.resetpassword.ResetPasswordRequest;
import com.mufid.dto.user.UserDTO;
import com.mufid.entity.bean.PasswordResetToken;
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

//    @Autowired
//    private EmailService emailService;

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

            // Send confirmation email
//            emailService.sendRegistrationConfirmation(user);

            return new AuthResponse(true, "Registration successful. Please check your email for confirmation.", null, null);

        } catch (Exception e) {
            logger.error("Registration failed", e);
            throw new AuthException("Registration failed: " + e.getMessage(), e);
        }
    }

//    @Override
//    public AuthResponse login(LoginRequest request) throws AuthException {
//        User user = userService.findByEmail(request.getEmail());
//
//        if (user == null) {
//            throw new AuthException("Invalid email or password");
//        }
//
//        // Check if account is locked
//        if (Boolean.TRUE.equals(user.getLocked())) {
//            throw new AuthException("Your account is locked. Please contact support or reset your password.");
//        }
//
//        // Check if account is active
//        if (Boolean.FALSE.equals(user.getActive())) {
//            throw new AuthException("Your account is not active. Please check your email to activate your account.");
//        }
//
//        // Validate password
//        if (!passwordService.matches(request.getPassword(), user.getPassword())) {
//            // Handle failed login attempt
//            userService.incrementLoginAttempt(user);
//
//            // Check if max attempts reached
//            if (user.getLoginAttempt() >= MAX_LOGIN_ATTEMPTS) {
//                userService.lockUser(user);
//                emailService.sendAccountLockedNotification(user);
//                throw new AuthException("Account locked due to too many failed login attempts. Please reset your password.");
//            }
//
//            throw new AuthException("Invalid email or password");
//        }
//
//        // Reset login attempts on successful login
//        userService.resetLoginAttempt(user);
//
//        // Get primary role for token
//        Role role = user.getUserRoleList().get(0).getRole();
//
//        // Create authentication token
//        Token token = tokenService.createToken(user, role);
//
//        // Convert user to DTO
//        UserDTO userDTO = userService.convertToDTO(user);
//
//        return new AuthResponse(true, "Login successful", token.getToken(), userDTO);
//    }

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
//                emailService.sendAccountLockedNotification(user);
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


    @Override
    public AuthResponse forgotPassword(ForgotPasswordRequest request) throws AuthException {
        User user = userService.findByEmail(request.getEmail());

        // For security reasons, don't reveal if email exists or not
        if (user == null) {
            logger.info("Password reset requested for non-existent email: {}", request.getEmail());
            return new AuthResponse(true, "If your email is registered, you will receive a password reset link.", null, null);
        }

        try {
            // Generate reset token
            String resetToken = passwordService.generateResetToken(user);

            // Send password reset email
//            emailService.sendPasswordResetLink(user, resetToken);

            return new AuthResponse(true, "If your email is registered, you will receive a password reset link.", null, null);

        } catch (Exception e) {
            logger.error("Password reset request failed", e);
            throw new AuthException("Failed to process password reset request", e);
        }
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) throws AuthException {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AuthException("Passwords do not match");
        }

        if (!passwordService.validateResetToken(request.getToken())) {
            throw new AuthException("Invalid or expired password reset token");
        }

        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(request.getToken());
        User user = resetToken.getUser();

        try {
            user.setPassword(passwordService.encodePassword(request.getNewPassword()));

            if (Boolean.TRUE.equals(user.getLocked())) {
                userService.unlockUser(user);
            }

            userService.resetLoginAttempt(user);

            resetToken.setUsed(true);
            passwordResetTokenRepository.save(resetToken);
        } catch (Exception e) {
            logger.error("Password reset failed", e);
            throw new AuthException("Failed to reset password: " + e.getMessage(), e);
        }
    }


    /*@Override
    public AuthResponse logout(LogoutRequest request) throws AuthException {
        try {
            tokenService.invalidateToken(request.getToken());
            return new AuthResponse(true, "Logout successful", null, null);
        } catch (Exception e) {
            logger.error("Logout failed", e);
            throw new AuthException("Logout failed: " + e.getMessage(), e);
        }
    }*/
}


