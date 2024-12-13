package io.github.adrianovictorn.user.rabbitMQ;


import io.github.adrianovictorn.user.constantes.RabbitMQConstantes;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import io.github.adrianovictorn.user.dto.UserDto;
import io.github.adrianovictorn.user.service.UserService;

@Service
public class UserMessageListener {

    private final UserService userService;

    public UserMessageListener(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = RabbitMQConstantes.FILA_PEDIDO)
    public void handleMessage(@Payload UserDto userDto) {
        userService.processUserUpdate(userDto);
        System.out.println("User update processed: " + userDto.getName());
    }
    
}