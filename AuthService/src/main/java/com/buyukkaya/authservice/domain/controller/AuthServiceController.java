package com.buyukkaya.authservice.domain.controller;

import com.buyukkaya.authservice.domain.model.exception.LoginServiceException;
import com.buyukkaya.authservice.domain.model.request.LoginRequest;
import com.buyukkaya.authservice.domain.model.request.RefreshAccessTokenRequest;
import com.buyukkaya.authservice.domain.model.request.RegisterRequest;
import com.buyukkaya.authservice.domain.model.response.ErrorResponse;
import com.buyukkaya.authservice.domain.model.response.LoginResponse;
import com.buyukkaya.authservice.domain.model.response.RegisterResponse;
import com.buyukkaya.authservice.domain.service.LoginService;
import com.buyukkaya.authservice.domain.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthServiceController {

    private final RegisterService registerService;
    private final LoginService loginService;

    public AuthServiceController(RegisterService registerService, LoginService service) {
        this.registerService = registerService;
        this.loginService = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(registerService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.loginUser(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<LoginResponse> getNewAccessToken(@RequestBody RefreshAccessTokenRequest request){
        return ResponseEntity.ok(loginService.refreshAccessToken(request));
    }

    @ExceptionHandler(LoginServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handeLoginServiceException(LoginServiceException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(Instant.now())
                .build());
    }

}
