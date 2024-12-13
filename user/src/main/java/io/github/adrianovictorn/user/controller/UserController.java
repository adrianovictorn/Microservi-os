package io.github.adrianovictorn.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.adrianovictorn.user.dto.UserDto;
import io.github.adrianovictorn.user.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listarUsuarios(){
        List<UserDto> usuariosCadastros = userService.listarTodosUsuarios();
        return ResponseEntity.ok(usuariosCadastros);
    }

    @PostMapping("cadastrar")
    public ResponseEntity<UserDto> cadastrarUsuario(@RequestBody UserDto user){
        UserDto novoUsuario = userService.cadastrarUsuario(user);
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> atualizarUsuario(@RequestBody UserDto user, @PathVariable Long id){
        UserDto usuarioAtualizado = userService.atualizarUsuario(id, user);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
}
