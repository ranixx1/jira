package com.example.jira.model;

import com.example.jira.enums.Escopo;
import com.example.jira.enums.Role;
import com.example.jira.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "chamado")

public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type")
    @NotNull(message = "O tipo é obrigatório")
    private Role role;

    @Column(name = "criado_por")
    private Usuario usuario;

    @Column(name = "horario_abertura")
    private LocalDateTime horario_abertura;

    @Column(name = "horario_atualização") // adicionar usúario que fez a ultima atualização
    private LocalDateTime horario_atualizacao;

    @Column(name = "status")
    private Status status;

    @Column(name = "titulo")
    @NotNull(message = "O titulo é obrigatório")
    private String titulo;

    @Column(name = "descricao")
    @NotNull(message = "A descrição é obrigatória")
    private String descricao;

    @Column(name = "compartilhar_com")
    private Escopo escopo;

    public Chamado(Role role, Usuario usuario, LocalDateTime horario_abertura, LocalDateTime horario_atualizazao,
            Status status, String titulo, String descricao, Escopo escopo) {
        this.role = role;
        this.usuario = usuario;
        this.horario_abertura = horario_abertura;
        this.horario_atualizacao = horario_atualizazao;
        this.titulo = titulo;
        this.status = status;
        this.descricao = descricao;
        this.escopo = escopo;
    }

    @Override
    public String toString() {
        return """
                Chamado
                ├─ Tipo      : %s
                ├─ Usuário   : %s
                ├─ Título    : %s
                ├─ Status    : %s
                ├─ Escopo    : %s
                ├─ Abertura  : %s
                └─ Descrição : %s
                """.formatted(
                this.role,
                this.usuario.getNome(),
                this.titulo,
                this.status,
                this.escopo,
                this.horario_abertura,
                this.descricao);
    }

}
