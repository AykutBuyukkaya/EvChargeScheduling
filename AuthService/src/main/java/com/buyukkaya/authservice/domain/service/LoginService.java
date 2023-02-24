package com.buyukkaya.authservice.domain.service;

import com.buyukkaya.authservice.domain.model.request.LoginRequest;
import com.buyukkaya.authservice.domain.model.request.RefreshAccessTokenRequest;
import com.buyukkaya.authservice.domain.model.response.LoginResponse;

public interface LoginService {

    String getAdminToken();

    LoginResponse loginUser(LoginRequest loginRequest);

    LoginResponse refreshAccessToken(RefreshAccessTokenRequest request);

}
