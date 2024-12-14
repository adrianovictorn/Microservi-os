package io.github.adrianovictorn.order.listener;

import io.github.adrianovictorn.order.dto.UserValidationMessage;
import io.github.adrianovictorn.order.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import static io.github.adrianovictorn.order.config.RabbitMQConfig.ORDER_QUEUE;

@Component
public class OrderListener {

    private final OrderRepository orderRepository;

    public OrderListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = ORDER_QUEUE)
    public void handleUserValidationResponse(UserValidationMessage message) {
        orderRepository.findById(message.getOrderId()).ifPresent(order -> {
            if (message.isValid()) {
                order.setStatus("APPROVED");
            } else {
                order.setStatus("REJECTED");
            }
            orderRepository.save(order);
        });
    }
}
