package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Funcionario;
import com.algaworks.awpag.domain.repository.FuncionarioRepository;
import com.algaworks.awpag.domain.service.CadastroFuncionarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final CadastroFuncionarioService cadastroFuncionarioService;
    private final FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listar(){
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<Funcionario> buscar(@PathVariable Long clientId){
        Optional<Funcionario> Funcionario = funcionarioRepository.findById(clientId);

        if(Funcionario.isPresent()){
            return ResponseEntity.ok(Funcionario.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Funcionario adicionar(@Valid @RequestBody Funcionario Funcionario){
        return cadastroFuncionarioService.salvar(Funcionario);
    }

    @PutMapping("/{funcionarioId}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long funcionarioId, @Valid @RequestBody Funcionario Funcionario){
        if (!funcionarioRepository.existsById(funcionarioId)){
            return ResponseEntity.notFound().build();
        }

        Funcionario.setId(funcionarioId);
        Funcionario = cadastroFuncionarioService.salvar(Funcionario);

        return ResponseEntity.ok(Funcionario);
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<Void> excluir(@PathVariable Long funcionarioId){
        if (!funcionarioRepository.existsById(funcionarioId)){
            return ResponseEntity.notFound().build();
        }

        cadastroFuncionarioService.excluir(funcionarioId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
