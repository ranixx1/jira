package com.example.jira.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.jira.model.Chamado;


public class ChamadoService {
    private List<Chamado> chamados = new ArrayList<>();
    public String listarChamados() {
        if (chamados.isEmpty()) {
            return "Lista de chamados está vazia";
        }
        return chamados.stream()
                .map(Chamado::toString)
                .collect(Collectors.joining("\n" + "=".repeat(30) + "\n"));

    }
}
