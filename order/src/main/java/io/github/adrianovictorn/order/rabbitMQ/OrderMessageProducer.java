package io.github.adrianovictorn.order.rabbitMQ;

import io.github.adrianovictorn.order.constantes.RabbitMQConstantes;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageProducer {
    private final AmqpTemplate amqpTemplate;

    public OrderMessageProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void enviarMensagem(String routingKey, Object mensagem) {
        amqpTemplate.convertAndSend(RabbitMQConstantes.EXCHANGE_NAME, routingKey, mensagem);
    }
}