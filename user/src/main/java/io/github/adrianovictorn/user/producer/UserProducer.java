package io.github.adrianovictorn.user.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import io.github.adrianovictorn.user.constantes.RabbitMQConstantes;
import io.github.adrianovictorn.user.dto.UserDto;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUser(UserDto userDto) {
        rabbitTemplate.convertAndSend(RabbitMQConstantes.FILA_PEDIDO, userDto);
    }
}
