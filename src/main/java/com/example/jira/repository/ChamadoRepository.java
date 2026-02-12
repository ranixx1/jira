package com.example.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jira.enums.*;
import com.example.jira.model.Chamado;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    List<Chamado> findByTipo(Tipo tipo);
    List<Chamado> findByStatus(Status status);
    List<Chamado> findByEscopo(Escopo escopo);
    List<Chamado> findByStatusAndEscopo(Status status, Escopo escopo);

}

