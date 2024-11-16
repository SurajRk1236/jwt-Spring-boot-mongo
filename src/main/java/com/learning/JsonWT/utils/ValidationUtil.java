package com.learning.JsonWT.utils;

import com.learning.JsonWT.entity.Token;
import com.learning.JsonWT.entity.User;
import com.learning.JsonWT.enums.CommonErrorResponse;
import com.learning.JsonWT.exceptions.GenericException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ValidationUtil {

    public static void validateUserExists(User user, String username) {
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public static void validateCorrectCreds(Authentication authentication) {
        if(!authentication.isAuthenticated()){
            throw new GenericException(CommonErrorResponse.JWTE001);
        }
    }

    public static void validateToken(Token storedToken, boolean tokenExpired) {
        if (storedToken == null && tokenExpired) {
            throw new GenericException(CommonErrorResponse.JWTE002);
        }
    }
}
