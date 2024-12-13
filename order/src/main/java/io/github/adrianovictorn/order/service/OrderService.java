package io.github.adrianovictorn.order.service;

import io.github.adrianovictorn.order.dto.OrderDto;
import io.github.adrianovictorn.order.model.Order;
import io.github.adrianovictorn.order.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto cadastrarPedido(OrderDto orderDto) {
        if (orderDto.getNome() == null || orderDto.getDescricao() == null || orderDto.getUser() == null) {
            throw new RuntimeException("Nome, descrição e usuário são obrigatórios.");
        }

        Order order = orderDto.toEntity();
        Order savedOrder = orderRepository.save(order);
        return OrderDto.fromEntity(savedOrder);
    }


    public List<OrderDto> listarTodosPedidos(){
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
