package com.example.jira.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jira.enums.Status;
import com.example.jira.model.Chamado;
import com.example.jira.service.ChamadoService;
import com.example.jira.enums.Tipo;
import com.example.jira.enums.Prioridade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chamados")
@RequiredArgsConstructor

public class ChamadoController {
    private final ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<Chamado> criarChamado(@RequestBody Chamado chamado) {
        Chamado novoChamado = chamadoService.criarChamado(chamado.getTipo(), chamado.getPrioridade(),
                chamado.getUsuario(), chamado.getTitulo(), chamado.getDescricao(), chamado.getEscopo());
        return ResponseEntity.status(201).body(novoChamado);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Chamado> buscarChamadoPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(chamadoService.buscarChamadoPorId(id));

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Chamado> alterarStatus(@PathVariable Integer id, @RequestBody Status novoStatus) {
        Chamado StatusAtualizado = chamadoService.alterarStatusChamado(id, novoStatus);

        return ResponseEntity.ok(StatusAtualizado);

    }

    @GetMapping
    public ResponseEntity<List<Chamado>> listarTodos() {
        return ResponseEntity.ok(chamadoService.listarChamados());

    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Chamado>> listarChamadosPorTipo(@PathVariable Tipo tipo) {
        return ResponseEntity.ok(chamadoService.listarChamadosPorTipo(tipo));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Chamado>> listarChamadoPorStatus(@PathVariable Status status) {
        return ResponseEntity.ok(chamadoService.listarChamadoPorStatus(status));
    }

    @GetMapping("/prioridade/{prioridade")
    public ResponseEntity<List<Chamado>> listarChamadosPorPrioridade(@PathVariable Prioridade prioridade) {
        return ResponseEntity.ok(chamadoService.listarChamadosPorPrioridade(prioridade));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Chamado>> listarChamadosPorCriador(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(chamadoService.listarPorCriador(usuarioId));
    }
}
/*
 * List<Chamado> findByTipo(Tipo tipo); OK
 * List<Chamado> findByStatus(Status status); OK
 * List<Chamado> findByEscopo(Escopo escopo); NECESSÁRIO?
 * List<Chamado> findByStatusAndEscopo(Status status, Escopo escopo); NECESSÁRIO?
 * List<Chamado> findByPrioridade(Prioridade prioridade); OK
 * List<Chamado> findByUsuarioId(Integer usuarioId); OK
 * 
 * 
 */