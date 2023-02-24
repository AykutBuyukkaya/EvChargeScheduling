package com.buyukkaya.authservice.domain.service.impl;

import com.buyukkaya.authservice.domain.model.exception.LoginServiceException;
import com.buyukkaya.authservice.domain.model.request.LoginRequest;
import com.buyukkaya.authservice.domain.model.request.RefreshAccessTokenRequest;
import com.buyukkaya.authservice.domain.model.response.LoginResponse;
import com.buyukkaya.authservice.domain.service.LoginService;
import com.buyukkaya.authservice.domain.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${keycloak.client_id}")
    private String clientId;

    @Value("${keycloak.grant_type_password}")
    private String grantTypePassword;

    @Value("${keycloak.grant_type_refresh_token}")
    private String grantTypeRefreshToken;

    @Value("${keycloak.client_secret}")
    private String clientSecret;

    @Value("${keycloak.admin_login_url}")
    private String adminLoginUrl;

    @Value("${keycloak.login_url}")
    private String userLoginUrl;

    private final RestTemplate restTemplate;
    private final CommonUtil commonUtil;

    public LoginServiceImpl(RestTemplate restTemplate, CommonUtil commonUtil) {
        this.restTemplate = restTemplate;
        this.commonUtil = commonUtil;
    }

    @Override
    public String getAdminToken() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username", "admin");
        multiValueMap.add("password", "admin");
        multiValueMap.add("client_id", clientId);
        multiValueMap.add("grant_type", grantTypePassword);
        multiValueMap.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> loginRequestHttpEntity = new HttpEntity<>(multiValueMap, httpHeaders);

        try {
            Optional<String> maybeAccessToken = Optional
                    .ofNullable(Objects.requireNonNull(restTemplate.exchange(adminLoginUrl, HttpMethod.POST, loginRequestHttpEntity, LoginResponse.class).
                                    getBody())
                            .getAccessToken());

            if (maybeAccessToken.isPresent())
                return maybeAccessToken.get();

            throw new LoginServiceException();

        } catch (Exception e) {
            log.error("Error while getting admin token", e);
            throw new LoginServiceException("An error occurred!");
        }
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username", loginRequest.getUsername());
        multiValueMap.add("password", loginRequest.getPassword());
        multiValueMap.add("client_id", clientId);
        multiValueMap.add("grant_type", grantTypePassword);
        multiValueMap.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> loginRequestHttpEntity = new HttpEntity<>(multiValueMap, httpHeaders);

        try {

            ResponseEntity<LoginResponse> loginResponse = Objects
                    .requireNonNull(restTemplate.exchange(userLoginUrl, HttpMethod.POST, loginRequestHttpEntity, LoginResponse.class));

            if (loginResponse.getStatusCode().is2xxSuccessful()) {
                log.info("User {} logged on.", loginRequest.getUsername());
                return loginResponse.getBody();
            } else {
                throw new LoginServiceException(Objects.requireNonNull(loginResponse.getBody()).getErrorMessage());
            }

        } catch (HttpClientErrorException.Unauthorized unauthorized) {

            log.error("Unauthorized exception for user {}", loginRequest.getUsername());
            throw new LoginServiceException("Invalid username or password!");

        } catch (Exception e) {

            log.error("Error during login process ", e);
            throw new LoginServiceException(commonUtil.parseExternalErrorMessage(e));

        }
    }

    public LoginResponse refreshAccessToken(RefreshAccessTokenRequest request) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("refresh_token", request.getRefreshToken());
        multiValueMap.add("client_id", clientId);
        multiValueMap.add("grant_type", grantTypeRefreshToken);
        multiValueMap.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> loginRequestHttpEntity = new HttpEntity<>(multiValueMap, httpHeaders);

        try {

            ResponseEntity<LoginResponse> loginResponse = Objects
                    .requireNonNull(restTemplate.exchange(userLoginUrl, HttpMethod.POST, loginRequestHttpEntity, LoginResponse.class));

            if (loginResponse.getStatusCode().is2xxSuccessful()) {
                return loginResponse.getBody();
            } else {
                throw new LoginServiceException(Objects.requireNonNull(loginResponse.getBody()).getErrorMessage());
            }

        } catch (Exception e) {

            log.error("Error during login process ", e);
            throw new LoginServiceException(commonUtil.parseExternalErrorMessage(e));

        }
    }

}
