package com.example.jira.service;

import com.example.jira.model.Usuario;
import com.example.jira.repository.UsuarioRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jira.enums.Time;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(String nome, String cpf, Time time) {

        if (repository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema");
        }
        Usuario usuario = new Usuario(nome, cpf, time);

        usuario = repository.save(usuario);

        String identificacao = String.format("%04d", usuario.getId());
        usuario.setMatricula(nome + "." + identificacao);

        return repository.save(usuario); // salva matrícula
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario buscarUsuarioPorCPF(String cpf) {
        return repository.findByCPF(cpf).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario atualizarTimePorId(Integer id, Time NovoTime) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setTime(NovoTime);
        return repository.save(usuario);

    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public List<Usuario> listarUsuariosPorTime(Time time) {
        return repository.findByTime(time);
    }

}
