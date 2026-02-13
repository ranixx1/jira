package com.example.jira.service;

import java.time.LocalDateTime;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.jira.enums.Escopo;
import com.example.jira.enums.Prioridade;
import com.example.jira.enums.Tipo;
import com.example.jira.enums.Status;
import com.example.jira.model.Chamado;
import com.example.jira.model.Usuario;
import com.example.jira.repository.ChamadoRepository;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public Chamado criarChamado(Tipo tipo, Prioridade prioridade, Usuario usuario, String titulo, String descricao, Escopo escopo) {
        LocalDateTime agora = LocalDateTime.now();
        Status statusInicial = Status.ABERTO;

        Chamado novoChamado = new Chamado(tipo,prioridade, usuario, agora, agora, statusInicial, titulo, descricao, escopo);
        return repository.save(novoChamado);
    }

    public Chamado fecharChamado(Integer id) {
        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.fechar();

        return repository.save(chamado);
    }


    public String listarChamados() {
        var chamados = repository.findAll();
        if (chamados.isEmpty()) {
            return "Lista de chamados está vazia";
        }
        return chamados.stream()
                .map(Chamado::toString)
                .collect(Collectors.joining("\n" + "=".repeat(30) + "\n"));

    }
}
