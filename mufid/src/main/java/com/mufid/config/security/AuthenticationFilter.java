package com.mufid.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mufid.dto.authentication.AuthResponse;
import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.Token;
import com.mufid.entity.bean.User;
import com.mufid.service.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isPublicEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);
        if (token == null) {
            sendUnauthorizedResponse(response, "Authentication token is missing");
            return;
        }

        if (!tokenService.validateToken(token)) {
            sendUnauthorizedResponse(response, "Invalid or expired token");
            return;
        }

        try {
            Token tokenEntity = tokenService.findByToken(token);

            if (tokenEntity == null) {
                logger.warn("Token not found in database: {}", token);
                sendUnauthorizedResponse(response, "Token not found");
                return;
            }

            User user = tokenEntity.getUser();
            if (user == null) {
                logger.warn("Token found but user is null: {}", token);
                sendUnauthorizedResponse(response, "User not found in token");
                return;
            }

            Role role = tokenEntity.getRole();
            if (role == null) {
                logger.warn("Token found but role is null: {}", token);
                sendUnauthorizedResponse(response, "Role not assigned to token");
                return;
            }

            setAuthentication(user, role, request);
            tokenService.updateLastActive(token);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error("Authentication failed: {}", e.getMessage(), e);
            sendUnauthorizedResponse(response, "Authentication failed due to server error");
        }
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/api/auth/login") ||
                path.contains("/api/auth/register") ||
                path.contains("/api/auth/forgot-password") ||
                path.contains("/api/auth/reset-password") ||
                path.contains("/api/auth/logout");
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
                ? bearerToken.substring(7)
                : null;
    }

    private void setAuthentication(User user, Role role, HttpServletRequest request) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase())
        );

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null, authorities
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), new AuthResponse(false, message, null, null));
    }
}
