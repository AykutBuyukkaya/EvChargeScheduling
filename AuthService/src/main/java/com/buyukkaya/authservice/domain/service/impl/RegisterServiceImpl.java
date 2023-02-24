package com.buyukkaya.authservice.domain.service.impl;

import com.buyukkaya.authservice.domain.mapper.RegisterMapper;
import com.buyukkaya.authservice.domain.model.exception.LoginServiceException;
import com.buyukkaya.authservice.domain.model.request.KeycloakRegisterRequest;
import com.buyukkaya.authservice.domain.model.request.RegisterRequest;
import com.buyukkaya.authservice.domain.model.response.RegisterResponse;
import com.buyukkaya.authservice.domain.service.LoginService;
import com.buyukkaya.authservice.domain.service.RegisterService;
import com.buyukkaya.authservice.domain.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Objects;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Value("${keycloak.register_url}")
    private String registrationUrl;

    private final LoginService loginService;
    private final RegisterMapper registerMapper;
    private final RestTemplate restTemplate;
    private final CommonUtil commonUtil;

    public RegisterServiceImpl(LoginService loginService, RegisterMapper registerMapper, RestTemplate restTemplate, CommonUtil commonUtil) {
        this.loginService = loginService;
        this.registerMapper = registerMapper;
        this.restTemplate = restTemplate;
        this.commonUtil = commonUtil;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        String adminAccessToken = loginService.getAdminToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(adminAccessToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<KeycloakRegisterRequest> registerRequestHttpEntity = new HttpEntity<>(registerMapper.mapToKeycloak(request), httpHeaders);

        try {
            ResponseEntity<RegisterResponse> registerResponseEntity = Objects
                    .requireNonNull(restTemplate.exchange(registrationUrl, HttpMethod.POST, registerRequestHttpEntity, RegisterResponse.class));

            if (registerResponseEntity.getStatusCode().is2xxSuccessful()) {
                RegisterResponse registerResponse = new RegisterResponse();
                registerResponse.setTimestamp(Instant.now());
                registerResponse.setMessage("User registration successful!");
                return registerResponse;
            } else {
                throw new LoginServiceException(Objects.requireNonNull(registerResponseEntity.getBody()).getErrorMessage());
            }
        } catch (Exception e) {
            log.error("Error during registration ", e);
            throw new LoginServiceException(commonUtil.parseExternalErrorMessage(e));
        }
    }

}
