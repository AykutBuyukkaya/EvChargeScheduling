package buyukkaya.userservice.domain.service.impl;

import buyukkaya.userservice.domain.mapper.EVUserMapper;
import buyukkaya.userservice.domain.model.request.UserRegistrationRequest;
import buyukkaya.userservice.domain.repository.EvUserRepository;
import buyukkaya.userservice.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final EvUserRepository evUserRepository;
    private final EVUserMapper evUserMapper;

    public UserServiceImpl(EvUserRepository evUserRepository, EVUserMapper evUserMapper) {
        this.evUserRepository = evUserRepository;
        this.evUserMapper = evUserMapper;
    }


    @RabbitListener(queues = "${rabbitconfig.user_registration_queue}")
    @Override
    public void register(UserRegistrationRequest request) {
        try {
            log.info("User registration via rabbitmq is started: {}", request.toString());
            evUserRepository.save(evUserMapper.toEVUser(request));
        } catch (Exception e) {
            log.error("Error during user registration via AuthService. Request {}, Error:", request, e);
        }


    }
}
