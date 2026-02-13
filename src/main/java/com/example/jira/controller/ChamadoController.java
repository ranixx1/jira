package com.example.jira.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jira.enums.Status;
import com.example.jira.model.Chamado;
import com.example.jira.service.ChamadoService;

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

    @PutMapping("/{id}/status")
    public ResponseEntity<Chamado> alterarStatus(@PathVariable Integer id, @RequestBody Status novoStatus){
        Chamado StatusAtualizado = chamadoService.alterarStatusChamado(id, novoStatus);

        return ResponseEntity.ok(StatusAtualizado);
    
    }

}
