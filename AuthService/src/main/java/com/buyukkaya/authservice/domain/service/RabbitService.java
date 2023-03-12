package com.buyukkaya.authservice.domain.service;

import com.buyukkaya.authservice.domain.model.response.RegisterResponse;

public interface RabbitService {

    void sendRegistrationToUserService(RegisterResponse registerResponse);

}
