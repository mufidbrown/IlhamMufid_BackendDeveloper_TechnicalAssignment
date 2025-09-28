package com.mufid.repository;


import com.mufid.entity.bean.PasswordResetToken;
import com.mufid.entity.bean.Token;
import com.mufid.entity.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByToken(String token);
    Optional<Token> findByUserId(Integer userId);
    Optional<PasswordResetToken> findByUser(User user);
}