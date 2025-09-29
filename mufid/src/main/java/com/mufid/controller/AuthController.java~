package com.mufid.controller;


import com.mufid.base.ApiResponse;
import com.mufid.shared.constant.MessageConstant;
import com.mufid.dto.authentication.AuthResponse;
import com.mufid.dto.login.LoginRequest;
import com.mufid.dto.register.RegisterRequest;
import com.mufid.exception.AuthException;
import com.mufid.service.authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request); // tanpa return
            return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_REGISTERED));
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_OPERATION, response));
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
