package io.github.adrianovictorn.order.producers;


import io.github.adrianovictorn.order.dto.UserValidationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import static io.github.adrianovictorn.order.config.RabbitMQConfig.USER_VALIDATION_QUEUE;

@Component
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUserValidationMessage(UserValidationMessage message) {
        rabbitTemplate.convertAndSend(USER_VALIDATION_QUEUE, message);
    }
}
