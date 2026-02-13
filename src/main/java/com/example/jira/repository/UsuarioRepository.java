package com.example.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jira.enums.Time;
import com.example.jira.model.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    boolean existsByCpf(String cpf);
    Optional<Usuario>findByMatricula(String matricula);
    List<Usuario>findByTime(Time time); 
    Optional<Usuario> findByCPF(String cpf);
}

/*
findByTime retorna uma lista com todos os participantes do time
*/
