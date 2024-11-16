package com.learning.JsonWT.controller;

import com.learning.JsonWT.requests.LoginRequestDTO;
import com.learning.JsonWT.requests.RegisterRequestDTO;
import com.learning.JsonWT.responses.CommonResponse;
import com.learning.JsonWT.service.AuthService;
import com.learning.JsonWT.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.learning.JsonWT.constants.SuccessMessageConstants.BEARER_TOKEN;
import static com.learning.JsonWT.constants.SuccessMessageConstants.USER_REGISTERED;

@RestController
@RequestMapping("/auth")
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public CommonResponse<String> register(@RequestBody @Valid RegisterRequestDTO registerRequest) {
        userService.registerUser(registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword());
        return CommonResponse.<String>builder().data(USER_REGISTERED).build();
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        String token = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return CommonResponse.<String>builder().data(String.format(BEARER_TOKEN, token)).build();
    }

    @PostMapping("/refresh-token")
    public CommonResponse<?> refreshToken(@RequestBody String refreshToken) {
            String newAccessToken = authService.refreshAccessToken(refreshToken);
            return CommonResponse.<String>builder().data(String.format(BEARER_TOKEN, newAccessToken)).build();
    }
}
