package com.example.jira.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.jira.enums.Escopo;
import com.example.jira.enums.Role;
import com.example.jira.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo é obrigatório")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "horario_abertura")
    private LocalDateTime horario_abertura;

    @Column(name = "horario_atualização") // adicionar usúario que fez a ultima atualização
    private LocalDateTime horario_atualizacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "titulo")
    @NotNull(message = "O titulo é obrigatório")
    private String titulo;

    @Column(name = "descricao")
    @NotNull(message = "A descrição é obrigatória")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Escopo escopo;

    @OneToMany(mappedBy = "chamado",cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Comentario>comentarios = new ArrayList<>();

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

    public void adicionarComentario(Comentario comentario){
        this.comentarios.add(comentario);
        this.horario_atualizacao = LocalDateTime.now();
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
