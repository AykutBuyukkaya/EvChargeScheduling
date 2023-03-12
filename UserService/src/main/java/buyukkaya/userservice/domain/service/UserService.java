package buyukkaya.userservice.domain.service;

import buyukkaya.userservice.domain.model.request.UserRegistrationRequest;

public interface UserService {

    void register(UserRegistrationRequest request);

}
