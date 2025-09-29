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

        logger.debug("Processing request to: {}", request.getRequestURI());
        logger.debug("Token extracted: {}", token != null ? "Yes" : "No");

        if (token == null) {
            logger.warn("No token found in request to: {}", request.getRequestURI());
            sendUnauthorizedResponse(response, "Authentication token is missing");
            return;
        }

        try {
            if (!tokenService.validateToken(token)) {
                logger.warn("Token validation failed for token: {}", token.substring(0, Math.min(token.length(), 10)) + "...");
                sendUnauthorizedResponse(response, "Invalid or expired token");
                return;
            }

            Token tokenEntity = tokenService.findByToken(token);

            if (tokenEntity == null) {
                logger.warn("Token not found in database");
                sendUnauthorizedResponse(response, "Token not found");
                return;
            }

            User user = tokenEntity.getUser();
            if (user == null) {
                logger.warn("User not found for token");
                sendUnauthorizedResponse(response, "User not found");
                return;
            }

            Role role = tokenEntity.getRole();
            if (role == null) {
                logger.warn("Role not found for token");
                sendUnauthorizedResponse(response, "Role not assigned");
                return;
            }

            logger.info("Authenticated user: {} with role: {}", user.getEmail(), role.getName());

            setAuthentication(user, role, request);

            tokenService.updateLastActive(token);

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error("Authentication error: {}", e.getMessage(), e);
            sendUnauthorizedResponse(response, "Authentication failed");
        }
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/api/v1/auth/login") ||
                path.contains("/api/v1/auth/register") ||
                path.contains("/api/v1/auth/forgot-password") ||
                path.contains("/api/v1/auth/reset-password") ||
                path.contains("/swagger-ui") ||
                path.contains("/v3/api-docs") ||
                path.contains("/error");
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void setAuthentication(User user, Role role, HttpServletRequest request) {
        // Normalisasi role agar sesuai format Spring Security
        String normalizedRole = "ROLE_" + role.getName().toUpperCase().replace(" ", "_");

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(normalizedRole)
        );

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null, authorities
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.debug("Authentication set for user: {} with authorities: {}", user.getEmail(), authorities);
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        new ObjectMapper().writeValue(response.getWriter(),
                new AuthResponse(false, message, null, null));
    }
}
