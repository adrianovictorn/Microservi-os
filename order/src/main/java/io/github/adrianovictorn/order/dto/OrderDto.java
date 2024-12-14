package io.github.adrianovictorn.order.dto;

import io.github.adrianovictorn.order.model.Order;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String nome;
    private final String descricao;
    private UserDto user;



    public OrderDto(Long id, String nome, String descricao, UserDto user) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.user = user;
    }

    public Order toEntity() {
        Order order = new Order();
        order.setId(this.id);
        order.setNome(this.nome);
        order.setDescricao(this.descricao);
        if (this.user != null) {
            order.setUserId(this.user.getId());
        }
        return order;
    }

    public static OrderDto fromEntity(Order order) {
        UserDto userDto = null;
        if (order.getUserId() != null) {
            userDto = new UserDto(order.getId());
        }
        return new OrderDto(order.getId(), order.getNome(), order.getDescricao(), userDto);
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
