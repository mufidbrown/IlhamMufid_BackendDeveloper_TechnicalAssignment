package com.mufid.service.token;

import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.Token;
import com.mufid.entity.bean.User;
import com.mufid.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token createToken(User user, Role role) {
        // Cek apakah user sudah punya token
        Optional<Token> optionalToken = tokenRepository.findByUserId(user.getId());

        // Generate random token
        String tokenString = UUID.randomUUID().toString();
        Date now = new Date();

        if (optionalToken.isPresent()) {
            Token existingToken = optionalToken.get();
            existingToken.setToken(tokenString);
            existingToken.setRole(role);
            existingToken.setLoginAt(now);
            existingToken.setLastActiveAt(now);
            existingToken.setLogoutAt(null); // Reset logout
            return tokenRepository.save(existingToken);
        } else {
            // Buat token baru
            Token token = new Token();
            token.setUser(user);
            token.setRole(role);
            token.setToken(tokenString);
            token.setLoginAt(now);
            token.setLastActiveAt(now);
            return tokenRepository.save(token);
        }
    }


    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void invalidateToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null) {
            tokenEntity.setLogoutAt(new Date());
            tokenRepository.save(tokenEntity);
        }
    }

    /*@Override
    public boolean validateToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);

        if (tokenEntity == null) {
            return false;
        }

        // Check if token is already logged out
        if (tokenEntity.getLogoutAt() != null) {
            return false;
        }

        return true;
    }*/
    @Override
    public boolean validateToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);

        if (tokenEntity == null) {
            System.out.println("Token tidak ditemukan di DB: " + token);
            return false;
        }
        if (tokenEntity.getLogoutAt() != null) {
            System.out.println("Token sudah logout: " + tokenEntity);
            return false;
        }
        if (tokenEntity.getExpiredAt() != null && tokenEntity.getExpiredAt().before(new Date())) {
            System.out.println("Token expired: " + tokenEntity);
            return false;
        }

        System.out.println("✅ Token valid: " + tokenEntity);
        return true;
    }

    @Override
    public void updateLastActive(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null) {
            tokenEntity.setLastActiveAt(new Date());
            tokenRepository.save(tokenEntity);
        }
    }
}