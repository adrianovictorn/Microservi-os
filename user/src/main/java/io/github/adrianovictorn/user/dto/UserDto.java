package io.github.adrianovictorn.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.adrianovictorn.user.model.User;
import java.io.Serializable;

public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String password;

    // Construtor com a anotação JsonCreator para controlar como os objetos são criados
    @JsonCreator
    public UserDto(@JsonProperty("id") Long id, 
                   @JsonProperty("name") String name, 
                   @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // Método para converter o DTO para uma entidade User
    public User toEntity() {
        User user = new User();
        user.setUserid(this.id);
        user.setName(this.name);
        user.setPassword(this.password);
        return user;
    }

    // Métodos Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método estático para converter a entidade User para o DTO
    public static UserDto fromEntity(User user) {
        return new UserDto(user.getUserid(), user.getName(), user.getPassword());
    }

    // Método toString (opcional, pode ser útil para depuração)
    @Override
    public String toString() {
        return "UserDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
