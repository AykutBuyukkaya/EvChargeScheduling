package com.buyukkaya.authservice.domain.service.impl;

import com.buyukkaya.authservice.domain.model.response.RegisterResponse;
import com.buyukkaya.authservice.domain.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;
    //TODO PUT QUEUE NAME INSTEAD OF THE QUEUE
    private final Queue queue;

    public RabbitServiceImpl(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @Override
    public void sendRegistrationToUserService(RegisterResponse registerResponse) {
        try {
            rabbitTemplate.convertAndSend(queue.getName(), registerResponse);
        } catch (Exception e) {
            log.error("Error during sending User Registration to UserService, request: {}, Error: ", registerResponse, e);
        }
    }
}
