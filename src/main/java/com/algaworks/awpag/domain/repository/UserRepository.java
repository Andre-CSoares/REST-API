package com.algaworks.awpag.domain.repository;

import com.algaworks.awpag.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNome(String nome);
    List<User> findByNomeContaining(String nome);
    Optional<User> findByEmail(String email);
}
