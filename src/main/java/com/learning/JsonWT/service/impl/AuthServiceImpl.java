package com.learning.JsonWT.service.impl;

import com.learning.JsonWT.entity.Token;
import com.learning.JsonWT.repository.TokenRepository;
import com.learning.JsonWT.repository.UsersRepository;
import com.learning.JsonWT.service.AuthService;
import com.learning.JsonWT.utils.JwtUtil;
import com.learning.JsonWT.utils.ValidationUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TokenRepository tokenRepository;
    //Todo Use constructor instead for better code which can be testable in junit

    @Override
    public String authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        ValidationUtil.validateCorrectCreds(authentication);
        String generatedToken = jwtUtil.generateToken(username, true);
        saveToken(generatedToken, username);
        return generatedToken;
    }

    @Override
    public String refreshAccessToken(String refreshToken) {
        Token storedToken = tokenRepository.findByTokenAndIsRefreshTokenTrue(refreshToken);
        ValidationUtil.validateToken(storedToken, jwtUtil.isTokenExpired(refreshToken));
        return jwtUtil.generateToken(storedToken.getUserId(), false);  // false for access token
    }

    private void saveToken(String generatedToken, String username) {
        Token tokenEntity = new Token();
        tokenEntity.setToken(generatedToken);
        tokenEntity.setUserId(username);
        tokenEntity.setRefreshToken(true);
        tokenEntity.setExpiredAt(jwtUtil.getExpirationDate(generatedToken));
        tokenRepository.save(tokenEntity);
    }
}
