package io.github.adrianovictorn.order.dto;

public class UserDto {

    private Long id;
    private String nome;

    // Construtor sem argumentos (necessário para Jackson)
    public UserDto() {
    }

    // Construtor com argumentos
    public UserDto(Long id) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
