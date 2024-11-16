package com.learning.JsonWT.controller;

import com.learning.JsonWT.responses.CommonResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.learning.JsonWT.constants.SuccessMessageConstants.PROTECTED_ENDPOINT_WITH_BEARER_TOKEN;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/protected")
    public CommonResponse<String> protectedEndpoint() {
        return CommonResponse.<String>builder().data(PROTECTED_ENDPOINT_WITH_BEARER_TOKEN).build();
    }
}
