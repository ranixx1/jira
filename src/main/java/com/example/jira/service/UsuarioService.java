package com.example.jira.service;

import com.example.jira.model.Usuario;
import com.example.jira.repository.UsuarioRepository;
import com.example.jira.enums.Time;

public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(String nome, Time time) {

        Usuario usuario = new Usuario(nome, time);

        usuario = repository.save(usuario);

        String identificacao = String.format("%04d", usuario.getId());
        usuario.setMatricula(nome + "." + identificacao);

        return repository.save(usuario);
    }

}
