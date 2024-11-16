package com.learning.JsonWT.service;

public interface AuthService {
    String authenticateUser(String username, String password);

    String refreshAccessToken(String refreshToken);
}
