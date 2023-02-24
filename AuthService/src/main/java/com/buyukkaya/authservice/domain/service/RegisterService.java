package com.buyukkaya.authservice.domain.service;

import com.buyukkaya.authservice.domain.model.request.RegisterRequest;
import com.buyukkaya.authservice.domain.model.response.RegisterResponse;

public interface RegisterService {

    RegisterResponse register(RegisterRequest request);

}
