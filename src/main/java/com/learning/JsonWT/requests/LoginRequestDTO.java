package com.learning.JsonWT.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.learning.JsonWT.constants.ErrorMessageConstants.PASSWORD_NOT_NULL;
import static com.learning.JsonWT.constants.ErrorMessageConstants.USER_NAME_NOT_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequestDTO {

    @NotNull(message = USER_NAME_NOT_NULL)
    String username;

    @NotNull(message = PASSWORD_NOT_NULL)
    String password;
}
