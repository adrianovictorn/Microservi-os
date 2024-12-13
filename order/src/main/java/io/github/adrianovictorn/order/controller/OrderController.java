package io.github.adrianovictorn.order.controller;

import java.util.List;

import io.github.adrianovictorn.order.constantes.RabbitMQConstantes;
import io.github.adrianovictorn.order.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.github.adrianovictorn.order.dto.OrderDto;
import io.github.adrianovictorn.order.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> listarTodosPedidos(){
        List<OrderDto> list = orderService.listarTodosPedidos();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<OrderDto> cadastrarPedido(@RequestBody OrderDto orderDto) {
        if (orderDto.getUser() == null || orderDto.getUser().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        OrderDto orderCadastrada = orderService.cadastrarPedido(orderDto);
        orderService.enviaMensagem(RabbitMQConstantes.FILA_PEDIDO, orderCadastrada);

        return ResponseEntity.ok(orderCadastrada);
    }


    @PutMapping("{id}") 
    public ResponseEntity<OrderDto> atualizarPedido(@RequestBody OrderDto orderDto, @PathVariable Long id){
        OrderDto orderExistente = orderService.atualizarPedido(orderDto, id);
        return ResponseEntity.ok(orderExistente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id){
        orderService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
