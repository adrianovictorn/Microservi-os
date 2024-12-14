package io.github.adrianovictorn.user.listener;

import io.github.adrianovictorn.user.config.RabbitMQConfig;
import io.github.adrianovictorn.user.dto.UserValidationMessage;
import io.github.adrianovictorn.user.producer.UserProducer;
import io.github.adrianovictorn.user.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener {

    private final UserService userService;
    private final UserProducer userProducer;

    public UserListener(UserService userService, UserProducer userProducer) {
        this.userService = userService;
        this.userProducer = userProducer;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_VALIDATION_QUEUE)
    public void validateUser(UserValidationMessage message) {
        boolean isValid = userService.validateUser(message.getUserId());
        message.setValid(isValid);

        userProducer.sendValidationResponse(message);
    }
}
