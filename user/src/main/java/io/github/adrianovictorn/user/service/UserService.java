package io.github.adrianovictorn.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.adrianovictorn.user.dto.UserDto;
import io.github.adrianovictorn.user.model.User;
import io.github.adrianovictorn.user.producer.UserProducer;
import io.github.adrianovictorn.user.repository.UserRepository;

@Service
public class UserService {
    

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    public List<UserDto> listarTodosUsuarios(){
        List<User> users = userRepository.findAll();
        return users.stream()
        .map(UserDto::fromEntity).collect(Collectors.toList());
    }

    public UserDto cadastrarUsuario(UserDto userDto) {
        User user = userDto.toEntity();
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = UserDto.fromEntity(savedUser);
        return savedUserDto;
    }

    public UserDto buscarPorId(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não cadastrado"));
        return UserDto.fromEntity(user);
    }

    public UserDto atualizarUsuario(Long id, UserDto userDto){
        User usuariocadastrado = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuariocadastrado.setName(userDto.getName());
        usuariocadastrado.setPassword(userDto.getPassword());
        userRepository.save(usuariocadastrado);
        return UserDto.fromEntity(usuariocadastrado);
    }

    public void deletarUsuario(Long id){
        User usuariocadastrado = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não cadastrado!"));
        userRepository.delete(usuariocadastrado);
    }

    public void processUserUpdate(UserDto userDto) {
        User user = new User();
        user.setUserid(userDto.getId());
        user.setName(userDto.getName());
        userRepository.save(user); // Salva ou atualiza o usuário na base local
    }



    public boolean validateUser(Long userId) {
        return userRepository.existsById(userId);
    }
}

