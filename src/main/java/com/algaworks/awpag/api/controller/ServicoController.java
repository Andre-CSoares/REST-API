package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Servico;
import com.algaworks.awpag.domain.service.CadastroServicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/servico")
public class ServicoController {
    private final CadastroServicoService cadastroServicoService;
    private final com.algaworks.awpag.domain.repository.ServicoRepository ServicoRepository;
    @GetMapping
    public List<Servico> listar(){
        return ServicoRepository.findAll();
    }

    @GetMapping("/{servicoId}")
    public ResponseEntity<Servico> buscar(@PathVariable Long servicoId){
        Optional<Servico> Servico = ServicoRepository.findById(servicoId);

        if(Servico.isPresent()){
            return ResponseEntity.ok(Servico.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Servico adicionar(@Valid @RequestBody Servico servicoId){
        return cadastroServicoService.salvar(servicoId);
    }

    @PutMapping("/{servicoId}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long servicoId, @Valid @RequestBody Servico Servico){
        if (!ServicoRepository.existsById(servicoId)){
            return ResponseEntity.notFound().build();
        }

        Servico.setId(servicoId);
        Servico = cadastroServicoService.salvar(Servico);

        return ResponseEntity.ok(Servico);
    }

    @DeleteMapping("/{servicoId}")
    public ResponseEntity<Void> excluir(@PathVariable Long servicoId){
        if (!ServicoRepository.existsById(servicoId)){
            return ResponseEntity.notFound().build();
        }

        cadastroServicoService.excluir(servicoId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
