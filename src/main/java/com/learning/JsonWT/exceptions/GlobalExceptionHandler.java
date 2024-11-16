package com.learning.JsonWT.exceptions;

import com.learning.JsonWT.responses.CommonResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

import static com.learning.JsonWT.constants.CommonConstants.DELIMITER;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResponse<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return getErrorResponse(e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(DELIMITER)));
    }

    @ExceptionHandler(GenericException.class)
    @ResponseBody
    public CommonResponse<?> handleException(GenericException exception) {
        return getErrorResponse(exception);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public CommonResponse<?> handleUserNameException(UsernameNotFoundException exception) {
        return getErrorResponse(exception.getMessage());
    }

    private CommonResponse<?> getErrorResponse(GenericException exception) {
        return CommonResponse.builder()
                .errorMessage(exception.getErrorResponse().getMessage())
                .code(exception.getErrorResponse().getCode()).build();
    }

    private CommonResponse<?> getErrorResponse(String message) {
        return CommonResponse.builder()
                .errorMessage(message).build();
    }
}
