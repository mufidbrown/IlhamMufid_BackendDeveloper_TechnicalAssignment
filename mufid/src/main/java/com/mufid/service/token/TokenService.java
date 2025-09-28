package com.mufid.service.token;

import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.Token;
import com.mufid.entity.bean.User;

public interface TokenService {
    Token createToken(User user, Role role);
    Token findByToken(String token);
    void invalidateToken(String token);
    boolean validateToken(String token);
    void updateLastActive(String token);
}
