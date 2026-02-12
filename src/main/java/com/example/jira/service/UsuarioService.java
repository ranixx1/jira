package com.example.jira.service;

import com.example.jira.model.Usuario;
import com.example.jira.repository.UsuarioRepository;
import com.example.jira.enums.Time;
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public Usuario criarUsuario(String nome, Time time) {

        Usuario usuario = new Usuario();

        usuario = repository.save(usuario); // Salva id

        String idStr = String.valueOf(usuario.getId());         // nome = ranilton, id = 1392849 -> ranilton.1392


        String identificacao = idStr.length() >= 4
                ? idStr.substring(0, 4)
                : idStr;

        String matricula = nome +"."+ identificacao;

        usuario.setMatricula(matricula);

        return repository.save(usuario);
    }

}
