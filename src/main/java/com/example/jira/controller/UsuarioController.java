package com.example.jira.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jira.enums.Time;
import com.example.jira.model.Usuario;
import java.util.List;

import com.example.jira.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor

public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {

        Usuario novoUsuario = usuarioService
                .criarUsuario(usuario.getNome(), usuario.getCpf(), usuario.getTime());

        return ResponseEntity.status(201).body(novoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorCPF(@PathVariable String CPF) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorCPF(CPF));
    }

    @PutMapping("/{id}/time")
    public ResponseEntity<Usuario> atualizarTimeUsuarioPorId(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario atualizado = usuarioService.atualizarTimePorId(id, usuario.getTime());
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/time/{time}")
    public ResponseEntity<List<Usuario>> listarPorTime(@PathVariable Time time) {
        return ResponseEntity.ok(usuarioService.listarUsuariosPorTime(time));
    }

}
