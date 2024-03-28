package com.algaworks.awpag.domain.service;

import com.algaworks.awpag.domain.model.Servico;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroServicoService {
    private final com.algaworks.awpag.domain.repository.ServicoRepository ServicoRepository;

    @Transactional
    public Servico salvar(Servico servico){
        return ServicoRepository.save(servico);
    }

    @Transactional
    public void excluir(Long servicoId) { ServicoRepository.deleteById(servicoId); }
}
