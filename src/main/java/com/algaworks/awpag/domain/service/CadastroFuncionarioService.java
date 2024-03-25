package com.algaworks.awpag.domain.service;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Funcionario;
import com.algaworks.awpag.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroFuncionarioService {
    private final FuncionarioRepository FuncionarioRepository;

    @Transactional
    public Funcionario salvar(Funcionario funcionario){
        boolean emailEmUso = FuncionarioRepository.findByEmail(funcionario.getEmail()).filter(c -> !c.equals(funcionario)).isPresent();

        if(emailEmUso){
            throw new NegocioException("Email já cadastrado. Utilize outro");
        }

        return FuncionarioRepository.save(funcionario);
    }

    @Transactional
    public void excluir(Long funcionarioId){
        FuncionarioRepository.deleteById(funcionarioId);
    }
}
