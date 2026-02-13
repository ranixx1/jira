package com.example.jira.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jira.enums.Escopo;
import com.example.jira.enums.Prioridade;
import com.example.jira.enums.Tipo;
import com.example.jira.enums.Status;
import com.example.jira.model.Chamado;
import com.example.jira.model.Usuario;
import com.example.jira.repository.ChamadoRepository;
import com.example.jira.repository.UsuarioRepository;

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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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
        Chamado chamado = repository.findById(id).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
        return chamado;
    }

    public Chamado fecharChamado(Integer id) {
        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.fechar();

        return repository.save(chamado);
    }

    public Chamado alterarStatusChamado(Integer id, Status novoStatus) {

        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.alterarStatus(novoStatus);

        return repository.save(chamado);
    }

    public List<Chamado> listarChamados() {
        var chamados = repository.findAll();
        if (chamados.isEmpty()) {
            throw new RuntimeException("Lista de chamados está vazia");
        }
        return chamados;

    }
    
    public List<Chamado> listarChamadosPorTipo(Tipo tipo){
        var procura = repository.findByTipo(tipo);
        if(procura.isEmpty()){
            throw new RuntimeException("Nenhum chamado encontrado");
        }
        return procura;
    }
}
