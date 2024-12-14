package io.github.adrianovictorn.order.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "order.user.exchange";
    public static final String ORDER_QUEUE = "order.queue";
    public static final String USER_VALIDATION_QUEUE = "user.validation.queue";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }



    @Bean
    public Queue userValidationQueue() {
        return new Queue(USER_VALIDATION_QUEUE, true);
    }

    @Bean
    public Binding bindingOrderQueue(Queue orderQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(orderQueue).to(directExchange).with(ORDER_QUEUE);
    }

    @Bean
    public Binding bindingUserValidationQueue(Queue userValidationQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(userValidationQueue).to(directExchange).with(USER_VALIDATION_QUEUE);
    }
}
