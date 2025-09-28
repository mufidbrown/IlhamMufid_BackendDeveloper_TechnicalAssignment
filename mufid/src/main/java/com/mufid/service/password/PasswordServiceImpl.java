package com.mufid.service.password;


import com.mufid.entity.bean.PasswordResetToken;
import com.mufid.entity.bean.User;
import com.mufid.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final long TOKEN_EXPIRY_MS = 60 * 1000;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public String encodePassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public boolean matches(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

/*    @Override
    public String generateResetToken(User user) {
        // Cek apakah user sudah punya token reset
        Optional<PasswordResetToken> optionalToken = passwordResetTokenRepository.findByUser(user);

        // Token baru & waktu expired 1 menit dari sekarang
        String newToken = UUID.randomUUID().toString();
        Date newExpiry = new Date(System.currentTimeMillis() + TOKEN_EXPIRY_MS);

        // Jika sudah ada token lama, hapus dulu
        optionalToken.ifPresent(passwordResetTokenRepository::delete);

        // Simpan token baru
        PasswordResetToken tokenEntity = new PasswordResetToken();
        tokenEntity.setUser(user);
        tokenEntity.setToken(newToken);
        tokenEntity.setExpiryDate(newExpiry);
        tokenEntity.setUsed(false);

        passwordResetTokenRepository.save(tokenEntity);

        return newToken;
    }*/


/*    @Override
    public boolean validateResetToken(String token) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);

        if (resetToken == null) {
            return false;
        }

        // Check if token is expired or used
        if (resetToken.isExpired() || resetToken.isUsed()) {
            return false;
        }

        return true;
    }*/
}
