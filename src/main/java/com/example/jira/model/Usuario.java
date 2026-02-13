package com.example.jira.model;

import com.example.jira.enums.Time;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @Column(name = "cpf", unique = true)
    @NotNull(message = "CPF é obrigatório")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Definir time é obrigatório")
    private Time time;

    @Column(updatable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(name = "matricula", unique = true)
    private String matricula;

    public Usuario(String nome, String cpf, Time time) {
        this.nome = nome;
        this.cpf = cpf;
        this.time = time;
    }

    @PrePersist
    public void prePersist() {
        this.dataHoraCriacao = LocalDateTime.now();
    }
}

/*
 * matricula é gerada diretamente no Service
 */
