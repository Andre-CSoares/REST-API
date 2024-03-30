package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.User;
import com.algaworks.awpag.domain.repository.UserRepository;
import com.algaworks.awpag.domain.service.CadastroUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final CadastroUserService cadastroUserService;
    private final UserRepository UserRepository;

    @GetMapping
    public List<User> listar(){
        return UserRepository.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> buscar(@PathVariable Long userId){
        Optional<User> User = UserRepository.findById(userId);

        if(User.isPresent()){
            return ResponseEntity.ok(User.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> atualizar(@PathVariable Long userId, @Valid @RequestBody User User){
        if (!UserRepository.existsById(userId)){
            return ResponseEntity.notFound().build();
        }

        User.setId(userId);
        User = cadastroUserService.salvar(User);

        return ResponseEntity.ok(User);
    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> excluir(@PathVariable Long userId){
        if (!UserRepository.existsById(userId)){
            return ResponseEntity.notFound().build();
        }

        cadastroUserService.excluir(userId);
        return ResponseEntity.noContent().build();
    }
}
