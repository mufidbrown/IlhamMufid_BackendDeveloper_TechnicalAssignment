package com.mufid.service.authentication;


import com.mufid.dto.authentication.AuthResponse;
import com.mufid.dto.forgotpassword.ForgotPasswordRequest;
import com.mufid.dto.login.LoginRequest;
import com.mufid.dto.register.RegisterRequest;
import com.mufid.dto.resetpassword.ResetPasswordRequest;
import com.mufid.exception.AuthException;

public interface AuthService {
    AuthResponse register(RegisterRequest request) throws AuthException;
    AuthResponse login(LoginRequest request) throws AuthException;
    AuthResponse forgotPassword(ForgotPasswordRequest request) throws AuthException;
    void resetPassword(ResetPasswordRequest request)  throws AuthException;
//    AuthResponse logout(LogoutRequest request) throws AuthException;
}
