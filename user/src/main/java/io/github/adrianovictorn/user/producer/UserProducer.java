package io.github.adrianovictorn.user.producer;

import io.github.adrianovictorn.user.dto.UserValidationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import io.github.adrianovictorn.user.dto.UserDto;

import static io.github.adrianovictorn.user.config.RabbitMQConfig.ORDER_QUEUE;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendValidationResponse(UserValidationMessage message) {
        rabbitTemplate.convertAndSend(ORDER_QUEUE, message);
    }
}

