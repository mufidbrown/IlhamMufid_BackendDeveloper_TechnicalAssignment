package com.mufid.repository;

import com.mufid.entity.bean.PasswordResetToken;
import com.mufid.entity.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);
//    List<PasswordResetToken> findByUser(User user);
    Optional<PasswordResetToken> findByUser(User user);

}
