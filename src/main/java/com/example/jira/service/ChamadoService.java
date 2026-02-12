package com.example.jira.service;

import java.time.LocalDateTime;

import java.util.stream.Collectors;

import com.example.jira.enums.Escopo;
import com.example.jira.enums.Role;
import com.example.jira.enums.Status;
import com.example.jira.model.Chamado;
import com.example.jira.model.Usuario;
import com.example.jira.repository.ChamadoRepository;

public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public Chamado criarChamado(Role role, Usuario usuario, String titulo, String descricao, Escopo escopo) {
        LocalDateTime agora = LocalDateTime.now();
        Status statusInicial = Status.ABERTO;

        Chamado novoChamado = new Chamado(role, usuario, agora, agora, statusInicial, titulo, descricao, escopo);
        return repository.save(novoChamado);
    }

    public Chamado fecharChamado(Integer id) {
        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        if (chamado.getStatus() == Status.FECHADO) {
            throw new IllegalArgumentException("O chamado já está fechado");

        }
        chamado.setStatus(Status.FECHADO);
        chamado.setHorario_atualizacao(LocalDateTime.now());
        return repository.save(chamado);

    }

    public String listarChamados() {
        if (repository.findAll().isEmpty()) {
            return "Lista de chamados está vazia";
        }
        return repository.findAll()
                .stream()
                .map(Chamado::toString)
                .collect(Collectors.joining("\n" + "=".repeat(30) + "\n"));

    }
}
