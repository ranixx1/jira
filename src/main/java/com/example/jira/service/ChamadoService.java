package com.example.jira.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.jira.enums.Escopo;
import com.example.jira.enums.Role;
import com.example.jira.enums.Status;
import com.example.jira.model.Chamado;
import com.example.jira.model.Usuario;

public class ChamadoService {
    private List<Chamado> chamados = new ArrayList<>();

    public void criarChamado(Role role, Usuario usuario, String titulo, String descricao, Escopo escopo) {
        LocalDateTime agora = LocalDateTime.now();
        Status statusInicial = Status.ABERTO;

        Chamado novoChamado = new Chamado(role, usuario, agora, agora, statusInicial, titulo, descricao, escopo);
        this.chamados.add(novoChamado);
        System.out.println("Chamado aberto com sucesso!");
    }

    public void fecharChamado(Chamado chamado) {
        if (chamado.getStatus() == Status.FECHADO) {
            throw new IllegalArgumentException("O chamado já está fechado");
        }
        chamado.setStatus(Status.FECHADO);
        chamado.setHorario_atualizacao(LocalDateTime.now()); // Atualiza e salva
        System.out.println("Chamado #" + chamado.getId() + " fechado com sucesso!");
    }

    public String listarChamados() {
        if (chamados.isEmpty()) {
            return "Lista de chamados está vazia";
        }
        return chamados.stream()
                .map(Chamado::toString)
                .collect(Collectors.joining("\n" + "=".repeat(30) + "\n"));

    }
}
