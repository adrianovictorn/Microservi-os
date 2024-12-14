package io.github.adrianovictorn.order.service;

import io.github.adrianovictorn.order.dto.UserValidationMessage;
import io.github.adrianovictorn.order.model.Order;
import io.github.adrianovictorn.order.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static io.github.adrianovictorn.order.config.RabbitMQConfig.USER_VALIDATION_QUEUE;

@Service
public class OrderValidationConsumer {

    private final OrderRepository orderRepository;

    public OrderValidationConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = USER_VALIDATION_QUEUE)
    public void processarMensagem(UserValidationMessage validationMessage) {
        Long userId = validationMessage.getUserId();  // Pegando o userId da mensagem
        Long orderId = validationMessage.getOrderId();  // Pegando o orderId da mensagem

        // Atualizando o pedido com o userId recebido.
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        order.setUserId(userId);  // Associe diretamente o ID do usuário ao pedido
        orderRepository.save(order);  // Salve o pedido atualizado
    }
}
