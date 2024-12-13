package io.github.adrianovictorn.order.dto;

import io.github.adrianovictorn.order.model.Order;
import org.apache.catalina.User;


import java.io.Serializable;


public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String nome;
    private final String descricao;
    private UserDto user;

    public OrderDto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;

    }

    public Order toEntity() {
        Order order = new Order();
        order.setId(this.id);
        order.setNome(this.nome);
        order.setDescricao(this.descricao);
        order.setUserId(this.user.getId());
        return order;
    }

    public static OrderDto fromEntity(Order order) {
        return new OrderDto(order.getId(), order.getNome(), order.getDescricao());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
}
