package com.learning.JsonWT.service.impl;

import com.learning.JsonWT.entity.Role;
import com.learning.JsonWT.entity.User;
import com.learning.JsonWT.enums.RoleName;
import com.learning.JsonWT.repository.RoleRepository;
import com.learning.JsonWT.repository.UsersRepository;
import com.learning.JsonWT.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole.getName()));
        return userRepository.save(user);
    }
}
