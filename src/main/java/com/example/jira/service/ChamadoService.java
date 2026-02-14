package com.example.jira.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jira.enums.Escopo;
import com.example.jira.enums.Prioridade;
import com.example.jira.enums.Tipo;
import com.example.jira.enums.Status;
import com.example.jira.model.Chamado;
import com.example.jira.model.Comentario;
import com.example.jira.model.Usuario;
import com.example.jira.repository.ChamadoRepository;
import com.example.jira.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public ChamadoService(ChamadoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Chamado criarChamado(Tipo tipo, Prioridade prioridade, Usuario usuario, String titulo, String descricao,
            Escopo escopo) {
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Status statusInicial = Status.ABERTO;

        Chamado novoChamado = new Chamado(
                tipo,
                prioridade,
                usuarioExistente,
                statusInicial,
                titulo,
                descricao,
                escopo);

        return repository.save(novoChamado);
    }

    public Chamado buscarChamadoPorId(Integer id) {
        Chamado chamado = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));
        return chamado;
    }

    public Chamado fecharChamado(Integer id) {
        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));

        chamado.fechar();

        return repository.save(chamado);
    }

    public Chamado alterarStatusChamado(Integer id, Status novoStatus) {

        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));

        chamado.alterarStatus(novoStatus);

        return repository.save(chamado);
    }

    public Chamado adicionarComentario(Integer chamadoId, Integer usuarioId, String mensagem) {
        Chamado chamado = repository.findById(chamadoId)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));

        if (chamado.getStatus() == Status.FECHADO) {
            throw new IllegalStateException("Não é possível adicionar comentários a um chamado fechado.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Comentario novoComentario = new Comentario(mensagem, usuario, chamado);

        chamado.adicionarComentario(novoComentario);
        return repository.save(chamado);
    }

    public List<Chamado> listarChamados() {
        var chamados = repository.findAll();
        if (chamados.isEmpty()) {
            throw new RuntimeException("Lista de chamados está vazia");
        }
        return chamados;

    }

    public List<Chamado> listarChamadosPorTipo(Tipo tipo) {
        return repository.findByTipo(tipo);
    }

    public List<Chamado> listarChamadoPorStatus(Status status) {

        return repository.findByStatus(status);
    }

    public List<Chamado> listarChamadosPorPrioridade(Prioridade prioridade) {
        return repository.findByPrioridade(prioridade);
    }

public List<Chamado> listarPorCriador(Integer usuarioId) {
    if (!usuarioRepository.existsById(usuarioId)) {
        throw new EntityNotFoundException("Usuário não encontrado");
    }
    return repository.findByUsuarioId(usuarioId);
}
}
