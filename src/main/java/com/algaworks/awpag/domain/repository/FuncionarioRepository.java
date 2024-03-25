package com.algaworks.awpag.domain.repository;

import com.algaworks.awpag.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByNome(String nome);
    List<Funcionario> findByNomeContaining(String nome);
    Optional<Funcionario> findByEmail(String email);

    //
}
