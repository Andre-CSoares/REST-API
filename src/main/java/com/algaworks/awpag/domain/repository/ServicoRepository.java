package com.algaworks.awpag.domain.repository;

import com.algaworks.awpag.domain.model.Funcionario;
import com.algaworks.awpag.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
