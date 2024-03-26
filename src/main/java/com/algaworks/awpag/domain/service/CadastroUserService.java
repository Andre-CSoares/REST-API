package com.algaworks.awpag.domain.service;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroUserService {
    private final com.algaworks.awpag.domain.repository.UserRepository UserRepository;

    @Transactional
    public User salvar(User user){
        boolean emailEmUso = UserRepository.findByEmail(user.getEmail()).filter(c -> !c.equals(user)).isPresent();

        if(emailEmUso){
            throw new NegocioException("Email já cadastrado. Utilize outro");
        }

        return UserRepository.save(user);
    }

    @Transactional
    public void excluir(Long userId){
        UserRepository.deleteById(userId);
    }
}
