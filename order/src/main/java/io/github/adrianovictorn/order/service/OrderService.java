package io.github.adrianovictorn.order.service;

import io.github.adrianovictorn.order.dto.OrderDto;
import io.github.adrianovictorn.order.dto.UserValidationMessage;
import io.github.adrianovictorn.order.model.Order;
import io.github.adrianovictorn.order.producers.OrderProducer;
import io.github.adrianovictorn.order.repository.OrderRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public OrderDto cadastrarPedido(OrderDto orderDto) {
        Order order = new Order();
        order.setDescricao(orderDto.getDescricao());
        order.setNome(orderDto.getNome());
        order.setUserId(orderDto.getUser().getId()); // Associar o ID do usuário à ordem
        order.setStatus("PENDING");

        // Enviar a mensagem de validação para o RabbitMQ
        UserValidationMessage validationMessage = new UserValidationMessage(orderDto.getUser().getId(), orderDto.getId());
        orderProducer.sendUserValidationMessage(validationMessage);

        // Salvar o pedido no banco de dados
        Order savedOrder = orderRepository.save(order);

        // Retornar o DTO do pedido salvo
        return OrderDto.fromEntity(savedOrder);
    }


    public List<OrderDto> listarTodosPedidos() {
        return orderRepository.findAll().stream().map(OrderDto::fromEntity).collect(Collectors.toList());
    }

    public OrderDto atualizarPedido(OrderDto orderDto, Long id){
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Pedido não encontrado!"));
        order.setDescricao(orderDto.getDescricao());
        order.setNome(orderDto.getNome());
        Order orderAtualizada = orderRepository.save(order);
        return OrderDto.fromEntity(orderAtualizada);
    }

    public void deletarPedido(Long id){
        orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não cadastrado"));
        orderRepository.deleteById(id);
    }


    public void enviaMensagem(String nome, Object mensagem){
        this.rabbitTemplate.convertAndSend(nome,mensagem);
    }

}
