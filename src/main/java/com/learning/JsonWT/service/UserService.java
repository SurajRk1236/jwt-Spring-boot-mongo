package com.learning.JsonWT.service;

import com.learning.JsonWT.entity.User;

public interface UserService {
    User registerUser(String username, String email, String password);
}
